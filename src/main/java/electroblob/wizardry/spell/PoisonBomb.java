package electroblob.wizardry.spell;

import electroblob.wizardry.EnumElement;
import electroblob.wizardry.EnumSpellType;
import electroblob.wizardry.EnumTier;
import electroblob.wizardry.entity.projectile.EntityPoisonBomb;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.world.World;

public class PoisonBomb extends Spell {

	public PoisonBomb() {
		super(EnumTier.APPRENTICE, 15, EnumElement.EARTH, "poison_bomb", EnumSpellType.ATTACK, 25, EnumAction.none, false);
	}

	@Override
	public boolean doesSpellRequirePacket(){
		return false;
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, int ticksInUse, float damageMultiplier, float rangeMultiplier, float durationMultiplier, float blastMultiplier) {
		
		if(!world.isRemote){
			EntityPoisonBomb poisonbomb = new EntityPoisonBomb(world, caster, damageMultiplier, blastMultiplier);
			world.spawnEntityInWorld(poisonbomb);
		}
		
		caster.swingItem();
		world.playSoundAtEntity(caster, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
		return true;
	}

	@Override
	public boolean cast(World world, EntityLiving caster, EntityLivingBase target, float damageMultiplier, float rangeMultiplier, float durationMultiplier, float blastMultiplier){
		
		if(target != null){
		
			if(!world.isRemote){
				EntityPoisonBomb poisonbomb = new EntityPoisonBomb(world, caster, damageMultiplier, blastMultiplier);
				poisonbomb.directTowards(target, 1.5f);
				world.spawnEntityInWorld(poisonbomb);
			}
			
			caster.swingItem();
			world.playSoundAtEntity(caster, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canBeCastByNPCs(){
		return true;
	}

}

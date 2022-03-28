package fr.samlegamer.moremushrooms.effects;

import fr.samlegamer.moremushrooms.MoreMushrooms;
import fr.samlegamer.moremushrooms.registry.MMRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class MMMobEffects extends MobEffect
{
	public MMMobEffects(MobEffectCategory p_19451_, int p_19452_)
	{
		super(p_19451_, p_19452_);
	}
	
	   public boolean isDurationEffectTick(int p_19455_, int p_19456_)
	   {
		      if (this == MMRegistry.MMEffects.MORTAL_POISON.get())
		      {
		         int i = 5 >> p_19456_;
		         if (i > 0)
		         {
		            return p_19455_ % i == 0;
		         }
		         else
		         {
		            return true;
		         }
		      }
		      else
		      {
		         return this == MobEffects.HUNGER;
		      }
	   }

	   public void applyEffectTick(LivingEntity p_19467_, int p_19468_)
	   {
		      if (this == MMRegistry.MMEffects.MORTAL_POISON.get())
		      {
		         p_19467_.hurt(MoreMushrooms.MORTAL_POISON, 3.0F);
		      }
		      else
		      {
		         p_19467_.heal((float)Math.max(4 << p_19468_, 0));
		      }
	}
}
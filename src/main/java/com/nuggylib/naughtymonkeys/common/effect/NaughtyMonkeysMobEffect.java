package com.nuggylib.naughtymonkeys.common.effect;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class NaughtyMonkeysMobEffect extends MobEffect {
    public NaughtyMonkeysMobEffect(MobEffectCategory p_19451_, int color) {
        super(p_19451_, color);
    }

    @Override
    public void applyEffectTick(LivingEntity affectedLivingEntity, int p_19468_) {
        super.applyEffectTick(affectedLivingEntity, p_19468_);

        if (this == NaughtyMonkeysEffects.POO_FLU.get()) {
            if (affectedLivingEntity.getHealth() > 1.0F) {
                affectedLivingEntity.hurt(DamageSource.MAGIC, 1.0F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {

        if (this == NaughtyMonkeysEffects.POO_FLU.get()) {
            int j = 25 >> p_19456_;
            if (j > 0) {
                return p_19455_ % j == 0;
            } else {
                return true;
            }
        }
        return super.isDurationEffectTick(p_19455_, p_19456_);
    }
}

package com.nuggylib.naughtymonkeys.common.effect;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class NaughtyMonkeysMobEffect extends MobEffect {

    public NaughtyMonkeysMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity affectedLivingEntity, int p_19468_) {
        super.applyEffectTick(affectedLivingEntity, p_19468_);

        if (this == NaughtyMonkeysEffects.POO_FLU.get()) {
            // Never reduce target below half health
            if (affectedLivingEntity.getHealth() > (affectedLivingEntity.getMaxHealth() / 2)) {
                affectedLivingEntity.hurt(DamageSource.MAGIC, 1.0F);
            }
        }
        if (this == NaughtyMonkeysEffects.STINKY.get()) {

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

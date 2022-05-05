package com.nuggylib.naughtymonkeys.common.entity.projectile;

import com.nuggylib.naughtymonkeys.common.registries.effect.NaughtyMonkeysEffects;
import com.nuggylib.naughtymonkeys.common.registries.entity.NaughtyMonkeysEntities;
import com.nuggylib.naughtymonkeys.common.registries.items.NaughtyMonkeysItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class ThrownMonkeyPoo extends ThrowableItemProjectile {

    public ThrownMonkeyPoo(Level p_37399_, LivingEntity p_37400_) {
        super(NaughtyMonkeysEntities.THROWN_MONKEY_POO.get(), p_37400_, p_37399_);
    }
    public ThrownMonkeyPoo(EntityType<ThrownMonkeyPoo> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult target) {
        super.onHitEntity(target);
        if (target.getEntity() instanceof LivingEntity livingTarget) {
            livingTarget.hurt(DamageSource.thrown(this, this.getOwner()), 1.0F);
            livingTarget.addEffect(new MobEffectInstance(NaughtyMonkeysEffects.POO_FLU.get(), 500));
        }
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();

        }
    }

    @Override
    protected Item getDefaultItem() {
        return NaughtyMonkeysItems.MONKEY_POO.get();
    }
}

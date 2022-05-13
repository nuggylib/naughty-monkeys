package com.nuggylib.naughtymonkeys.common.world.damagesource;

import com.nuggylib.naughtymonkeys.common.world.entity.projectile.AbstractMonkeyPoo;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class NaughtyMonkeysDamageSource extends DamageSource {
    public NaughtyMonkeysDamageSource(String p_19333_) {
        super(p_19333_);
    }

    public static DamageSource monkeyPoo(AbstractMonkeyPoo abstractMonkeyPoo, @Nullable Entity entity) {
        return (new IndirectEntityDamageSource("monkey_poo", abstractMonkeyPoo, entity)).setProjectile();
    }
}

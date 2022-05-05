package com.nuggylib.naughtymonkeys.common.registries.effect;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NaughtyMonkeysEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NaughtyMonkeys.ID);

    public static final RegistryObject<MobEffect> POO_FLU = MOB_EFFECTS.register(NaughtyMonkeys.prefix("poo_flu"), () -> )
    
}

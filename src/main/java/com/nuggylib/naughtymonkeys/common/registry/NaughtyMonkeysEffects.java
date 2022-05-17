package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.effect.NaughtyMonkeysMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NaughtyMonkeysEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NaughtyMonkeys.ID);

    public static final RegistryObject<MobEffect> POO_FLU = MOB_EFFECTS.register("poo_flu", () -> new NaughtyMonkeysMobEffect(MobEffectCategory.HARMFUL, 5149489));
    public static final RegistryObject<MobEffect> STINKY = MOB_EFFECTS.register("stinky", () -> new NaughtyMonkeysMobEffect(MobEffectCategory.NEUTRAL, 5149489));
    
}

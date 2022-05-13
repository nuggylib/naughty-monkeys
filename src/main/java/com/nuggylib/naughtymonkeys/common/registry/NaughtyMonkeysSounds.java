package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NaughtyMonkeysSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NaughtyMonkeys.ID);

    public static final RegistryObject<SoundEvent> MONKEY_POO = SOUNDS.register("entity.monkey_poo.hit", () -> new SoundEvent(new ResourceLocation("entity.monkey_poo.hit")));

}

package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NaughtyMonkeys.ID);

    public static SoundEvent MONKEY_POO;

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        MONKEY_POO = registerSound("entity.monkey_poo.hit");
    }

    public static SoundEvent registerSound(String name) {
        ResourceLocation shotSoundLocation = new ResourceLocation(NaughtyMonkeys.ID, name);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        return soundEvent;
    }
}

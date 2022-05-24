package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysSounds {
    public static SoundEvent MONKEY_POO;
    public static SoundEvent MONKEY_INQUISITIVE;
    public static SoundEvent MONKEY_ANGRY;
    public static SoundEvent MONKEY_AMBIENT;
    public static SoundEvent MONKEY_HURT;
    public static SoundEvent MONKEY_DEATH;
    public static SoundEvent MONKEY_HAPPY;


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        MONKEY_POO = registerSound("entity.monkey_poo.hit");
        MONKEY_INQUISITIVE = registerSound("entity.monkey_inquisitive_2.curious");
        MONKEY_ANGRY = registerSound("entity.monkey_angry.growl");
        MONKEY_AMBIENT = registerSound("entity.monkey_inquisitive_1.ambient");
        MONKEY_DEATH = registerSound("entity.monkey_death.death");
        MONKEY_HURT = registerSound("entity.monkey_hurt.hurt");
        MONKEY_HAPPY = registerSound("entity.monkey_happy.happy");
    }

    public static SoundEvent registerSound(String name) {
        ResourceLocation shotSoundLocation = new ResourceLocation(NaughtyMonkeys.ID, name);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        return soundEvent;
    }
}

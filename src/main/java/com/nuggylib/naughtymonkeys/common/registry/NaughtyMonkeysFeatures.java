package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysFeatures {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> spawns = event.getSpawns().getSpawner(MobCategory.CREATURE);

        // Remove "default" spawn logic inherited for Monkeys
        spawns.removeIf(e -> e.type == NaughtyMonkeysEntities.MONKEY.get());
        // Make monkeys spawn more
        spawns.add(new MobSpawnSettings.SpawnerData(NaughtyMonkeysEntities.MONKEY.get(), 10000, 1, 4));

        BiomeSpecialEffects effects = event.getEffects();


    }

}

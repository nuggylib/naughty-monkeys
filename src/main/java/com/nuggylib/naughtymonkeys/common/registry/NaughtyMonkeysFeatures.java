package com.nuggylib.naughtymonkeys.common.registry;

import com.google.common.collect.ImmutableList;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.world.level.levelgen.feature.BananaPlantFoliagePlacer;
import com.nuggylib.naughtymonkeys.common.world.level.levelgen.feature.treedecorator.BananasDecorator;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysFeatures {

    public static final PlacedFeature BANANA_PLANT = VegetationFeatures.TREES_JUNGLE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(50, 0.1F, 1)));

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> spawns = event.getSpawns().getSpawner(MobCategory.CREATURE);
        // Remove "default" spawn logic inherited for Monkeys
        spawns.removeIf(e -> e.type == NaughtyMonkeysEntities.MONKEY.get());
        // Make monkeys spawn more
        spawns.add(new MobSpawnSettings.SpawnerData(NaughtyMonkeysEntities.MONKEY.get(), 10000, 1, 4));

        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            BiomeGenerationSettingsBuilder settingsBuilder = event.getGeneration();

            // TODO: Refer to TreePlacements; I need a banana plant version of that
            settingsBuilder.addFeature(0, () -> BANANA_PLANT);
        }
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leavesBlock), new BananaPlantFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBananaPlant() {
        return createStraightBlobTree(NaughtyMonkeysBlocks.BANANA_STEM.get(), NaughtyMonkeysBlocks.BANANA_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

}

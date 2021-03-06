package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.world.level.levelgen.feature.BananaPlantFoliagePlacer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class NaughtyMonkeysConfiguredFeatures {

    public static ConfiguredFeature<TreeConfiguration, ?> BANANA_PLANT;

    public static void init() {
        BANANA_PLANT = register("banana_plant", Feature.TREE.configured(createBananaPlant().build()));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leavesBlock), new BananaPlantFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBananaPlant() {
        return createStraightBlobTree(NaughtyMonkeysBlocks.BANANA_STEM.get(), NaughtyMonkeysBlocks.BANANA_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

}

package com.nuggylib.naughtymonkeys.common.block.grower;

import com.google.common.collect.ImmutableList;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysFeatures;
import com.nuggylib.naughtymonkeys.common.world.level.levelgen.feature.BananaPlantFoliagePlacer;
import com.nuggylib.naughtymonkeys.common.world.level.levelgen.feature.treedecorator.BananasDecorator;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BananaPlantGrower extends AbstractTreeGrower {

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leavesBlock), new BananaPlantFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBananaPlant() {
        return createStraightBlobTree(NaughtyMonkeysBlocks.BANANA_STEM.get(), NaughtyMonkeysBlocks.BANANA_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random p_60014_, boolean hasBeeHive) {
        return Feature.TREE.configured(createBananaPlant().decorators(ImmutableList.of(new BananasDecorator(1.0F))).ignoreVines().build());
    }
}

package com.nuggylib.naughtymonkeys.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class BananaPlantFoliagePlacer extends FoliagePlacer {
    public BananaPlantFoliagePlacer(IntProvider p_161411_, IntProvider p_161412_) {
        super(p_161411_, p_161412_);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        // TODO: Create, and register a FoliagePlacerType for the Banana plant (see also BlobFoliagePlacer)
        return null;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> stateBiConsumer, Random random, TreeConfiguration treeConfiguration, int p_161426_, FoliageAttachment foliageAttachment, int min, int mid, int max) {
        for(int i = max; i >= max - min; --i) {
            int j = Math.max(mid + foliageAttachment.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRow(reader, stateBiConsumer, random, treeConfiguration, foliageAttachment.pos(), j, i, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_) {
        // Banana plant foliage is always 3 blocks high
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(Random p_68562_, int p_68563_, int p_68564_, int p_68565_, int p_68566_, boolean p_68567_) {
        return false;
    }
}

package com.nuggylib.naughtymonkeys.common.feature;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nuggylib.naughtymonkeys.common.registries.NaughtyMonkeysFoliagePlacers;
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
    protected final int height;
    public static final Codec<BananaPlantFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68427_) -> blobParts(p_68427_).apply(p_68427_, BananaPlantFoliagePlacer::new));
    protected static <P extends BananaPlantFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> p_68414_) {
        return foliagePlacerParts(p_68414_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_68412_) -> {
            return p_68412_.height;
        }));
    }

    /**
     * @param unusedHeight              Only necessary so the method signature matches what's expected in the CODEC above
     */
    public BananaPlantFoliagePlacer(IntProvider radius, IntProvider offset, int unusedHeight) {
        super(radius, offset);
        this.height = 3;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return NaughtyMonkeysFoliagePlacers.BANANA_PLANT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> posAndStateBiConsumer, Random random, TreeConfiguration tree, int p_161426_, FoliageAttachment foliageAttachment, int min, int mid, int max) {
        for(int i = max; i >= max - min; --i) {
            int j = Math.max(mid + foliageAttachment.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRow(reader, posAndStateBiConsumer, random, tree, foliageAttachment.pos(), j, i, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(Random p_68562_, int p_68563_, int p_68564_, int p_68565_, int p_68566_, boolean p_68567_) {
        return false;
    }

    @Override
    public void placeLeavesRow(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> posAndStateBiConsumer, Random random, TreeConfiguration tree, BlockPos logBlockPos, int p_161443_, int p_161444_, boolean isDoubleTrunk) {
        int i = isDoubleTrunk ? 1 : 0;
        BlockPos.MutableBlockPos newLeafBlockPosition = new BlockPos.MutableBlockPos();

        for(int j = -p_161443_; j <= p_161443_ + i; ++j) {
            for(int k = -p_161443_; k <= p_161443_ + i; ++k) {
                if (!this.shouldSkipLocationSigned(random, j, p_161444_, k, p_161443_, isDoubleTrunk)) {
                    newLeafBlockPosition.setWithOffset(logBlockPos, j, p_161444_, k);
                    tryPlaceLeaf(reader, posAndStateBiConsumer, random, tree, newLeafBlockPosition);
                }
            }
        }

    }
}

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

import static java.lang.Math.abs;

public class BananaPlantFoliagePlacer extends FoliagePlacer {
    /**
     * <h3>The number of log blocks at the top of the given tree's "trunk" to be covered by foliage; the "height" of the
     * foliage blocks in contact with the tree's trunk</h3>
     * <p>
     *     For example, if a tree's trunk is 5 blocks high, and this <code>height</code> value was set to 3, then the top
     *     three log blocks would be covered by foliage and the last two blocks would be the "exposed" (or bottom) part
     *     of the tree.
     * </p>
     * <br />
     * <p>
     *     As an example for an edge case, if you have a tree whose trunk is 5 blocks high, but this <code>height</code> value
     *     is set to something <b>higher</b> than the height (such as 10), then the tree simply wouldn't have any exposed
     *     portion of the trunk; all trunk logs would be covered by foliage in this case.
     * </p>
     * 
     */
    protected final int height;
    
    public static final Codec<BananaPlantFoliagePlacer> CODEC = RecordCodecBuilder.create((plantFoliagePlacerInstance) -> blobParts(plantFoliagePlacerInstance).apply(plantFoliagePlacerInstance, BananaPlantFoliagePlacer::new));
    
    protected static <P extends BananaPlantFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> plantFoliagePlacerInstance) {
        return foliagePlacerParts(plantFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height").forGetter((placer) -> placer.height));
    }

    /**
     * @param unusedHeight              Only necessary so the method signature matches what's expected in the CODEC above
     */
    public BananaPlantFoliagePlacer(IntProvider radius, IntProvider offset, int unusedHeight) {
        super(radius, offset);
        this.height = 2;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return NaughtyMonkeysFoliagePlacers.BANANA_PLANT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> posAndStateBiConsumer, Random random, TreeConfiguration tree, int p_161426_, FoliageAttachment foliageAttachment, int offset, int mid, int count) {
        this.placeLeavesRow(reader, posAndStateBiConsumer, random, tree, foliageAttachment.pos(), 3, -1, foliageAttachment.doubleTrunk());
        this.placeLeavesRow(reader, posAndStateBiConsumer, random, tree, foliageAttachment.pos(), 1, 0, foliageAttachment.doubleTrunk());
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_) {
        return this.height;
    }

    /**
     * Determine if the foliage placer should skip placing the given leaf block position
     */
    @Override
    protected boolean shouldSkipLocation(Random random, int xOffset, int yOffset, int zOffset, int limitFromTrunk, boolean isDoubleTrunk) {
        if (xOffset == 0) {
            return false;
        }
        return zOffset != 0;
    }

    /**
     * <b>Places a "row" of leaf blocks</b>
     * 
     * @param reader
     * @param posAndStateBiConsumer
     * @param random
     * @param tree
     * @param logBlockPos
     * @param limitFromTrunk                    The max distance of leaf blocks extending out from the trunk (does not include trunk in count)
     * @param topLogOffset                      The offset count of blocks from the top log; determines where the leaves row will start, vertically (if set to 0, the row will be set "on top of" the last log block)
     * @param isDoubleTrunk
     */
    @Override
    public void placeLeavesRow(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> posAndStateBiConsumer, Random random, TreeConfiguration tree, BlockPos logBlockPos, int limitFromTrunk, int topLogOffset, boolean isDoubleTrunk) {
        int trunkOffset = isDoubleTrunk ? 1 : 0;

        // Instantiate the leaf block to be placed
        BlockPos.MutableBlockPos newLeafBlockPosition = new BlockPos.MutableBlockPos();

        // Double loop to create a "square" of leaf blocks; a single square level is the same thing as a "row" for this logic
        for(int xOffset = -limitFromTrunk; xOffset <= limitFromTrunk + trunkOffset; ++xOffset) {
            for(int zOffset = -limitFromTrunk; zOffset <= limitFromTrunk + trunkOffset; ++zOffset) {
                if (!this.shouldSkipLocation(random, xOffset, topLogOffset, zOffset, limitFromTrunk, isDoubleTrunk)) {

                    // Mutate the new leaf block's position - starts from the base log block position, then applies offset
                    newLeafBlockPosition.setWithOffset(logBlockPos, xOffset, topLogOffset, zOffset);

                    // Attempt to place the leaf block
                    tryPlaceLeaf(reader, posAndStateBiConsumer, random, tree, newLeafBlockPosition);
                }
            }
        }

    }

}

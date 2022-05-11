package com.nuggylib.naughtymonkeys.common.feature.treedecorator;

import com.mojang.serialization.Codec;
import com.nuggylib.naughtymonkeys.common.block.BlockBanana;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

/**
 * <b>Banana decorator</b>
 * <p>
 *     This class contains logic controlling how and when a Banana crop block is placed. It's based on the vanilla
 *     {@link net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator} class.
 * </p>
 */
public class BananasDecorator extends TreeDecorator {
    public static final Codec<BananasDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(BananasDecorator::new, (bananasDecorator) -> bananasDecorator.probability).codec();
    private final float probability;

    public BananasDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> type() {
        return NaughtyMonkeysTreeDecorators.BANANAS.get();
    }

    public void place(LevelSimulatedReader levelReader, BiConsumer<BlockPos, BlockState> blockPosBlockStateBiConsumer, Random random, List<BlockPos> targetBlockPositions, List<BlockPos> p_161723_) {
        if (!(random.nextFloat() >= this.probability)) {
            int firstTargetBlockY = targetBlockPositions.get(0).getY();
            targetBlockPositions.stream().filter((blockPos) -> blockPos.getY() - firstTargetBlockY <= 2).forEach((targetBlockPos) -> {
                for(Direction face : Direction.Plane.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        Direction faceOpposite = face.getOpposite();
                        BlockPos blockpos = targetBlockPos.offset(faceOpposite.getStepX(), 0, faceOpposite.getStepZ());
                        if (Feature.isAir(levelReader, blockpos)) {
                            blockPosBlockStateBiConsumer.accept(blockpos, NaughtyMonkeysBlocks.BANANAS.get().defaultBlockState().setValue(BlockBanana.AGE, Integer.valueOf(random.nextInt(3))).setValue(BlockBanana.FACING, face));
                        }
                    }
                }

            });
        }
    }
}

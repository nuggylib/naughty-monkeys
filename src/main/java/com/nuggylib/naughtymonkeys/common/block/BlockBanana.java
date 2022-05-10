package com.nuggylib.naughtymonkeys.common.block;

import com.nuggylib.naughtymonkeys.common.registry.tag.NaughtyMonkeysBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

/**
 * Inspired by the Minecraft {@link net.minecraft.world.level.block.CocoaBlock} class
 */
public class BlockBanana extends HorizontalDirectionalBlock implements BonemealableBlock {
    public BlockBanana(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_50897_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_50901_, Random p_50902_, BlockPos p_50903_, BlockState p_50904_) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_50893_, Random p_50894_, BlockPos p_50895_, BlockState p_50896_) {

    }

    /**
     * <b>Determines if bananas can grow on the target block</b>
     * <p>
     *     Although this method is deprecated, it appears as though Minecraft still uses this for Cocoa crops. Presumably,
     *     this method used to be used for more crop types, which no longer appears to be the case. In short, this method
     *     is an old method, but it's necessary for the banana crop block to behave similar to the Cocoa crop.
     * </p>
     * @param bananasBlockPos
     * @param levelReader
     * @param targetBlockPos
     * @return                          true if the "facing" block is a banana stem, otherwise false
     */
    public boolean canSurvive(BlockState bananasBlockPos, LevelReader levelReader, BlockPos targetBlockPos) {
        BlockState blockstate = levelReader.getBlockState(targetBlockPos.relative(bananasBlockPos.getValue(FACING)));
        return blockstate.is(NaughtyMonkeysBlockTags.BANANA_STEM);
    }
}

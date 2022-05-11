package com.nuggylib.naughtymonkeys.common.block;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.tag.NaughtyMonkeysBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

/**
 * Inspired by the Minecraft {@link net.minecraft.world.level.block.CocoaBlock} class
 */
public class BlockBanana extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    protected static final VoxelShape[] EAST_AABB = new VoxelShape[]{
            Block.box(11.0D, 7.0D, 6.0D, 15.0D, 12.0D, 10.0D),
            Block.box(9.0D, 5.0D, 5.0D, 15.0D, 12.0D, 11.0D),
            Block.box(7.0D, 3.0D, 4.0D, 15.0D, 12.0D, 12.0D),
            Block.box(7.0D, 3.0D, 3.0D, 15.0D, 12.0D, 13.0D)
    };
    protected static final VoxelShape[] WEST_AABB = new VoxelShape[]{
            Block.box(1.0D, 7.0D, 6.0D, 5.0D, 12.0D, 10.0D),
            Block.box(1.0D, 5.0D, 5.0D, 7.0D, 12.0D, 11.0D),
            Block.box(1.0D, 3.0D, 4.0D, 9.0D, 12.0D, 12.0D),
            Block.box(1.0D, 3.0D, 3.0D, 9.0D, 12.0D, 13.0D)
    };
    protected static final VoxelShape[] NORTH_AABB = new VoxelShape[]{
            Block.box(6.0D, 7.0D, 1.0D, 10.0D, 12.0D, 5.0D),
            Block.box(5.0D, 5.0D, 1.0D, 11.0D, 12.0D, 7.0D),
            Block.box(4.0D, 3.0D, 1.0D, 12.0D, 12.0D, 9.0D),
            Block.box(3.0D, 3.0D, 1.0D, 13.0D, 12.0D, 9.0D)
    };
    protected static final VoxelShape[] SOUTH_AABB = new VoxelShape[]{
            Block.box(6.0D, 7.0D, 11.0D, 10.0D, 12.0D, 15.0D),
            Block.box(5.0D, 5.0D, 9.0D, 11.0D, 12.0D, 15.0D),
            Block.box(4.0D, 3.0D, 7.0D, 12.0D, 12.0D, 15.0D),
            Block.box(3.0D, 3.0D, 7.0D, 13.0D, 12.0D, 15.0D)
    };

    public BlockBanana(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    public boolean isRandomlyTicking(BlockState bananasBlockState) {
        return bananasBlockState.getValue(AGE) < 3;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_50897_, BlockPos p_50898_, BlockState bananasBlockState, boolean p_50900_) {
        return bananasBlockState.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level p_50901_, Random p_50902_, BlockPos p_50903_, BlockState p_50904_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, Random random, BlockPos blockPos, BlockState bananasBlockState) {
        serverLevel.setBlock(blockPos, bananasBlockState.setValue(AGE, Integer.valueOf(bananasBlockState.getValue(AGE) + 1)), 3);
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
    @Override
    public boolean canSurvive(BlockState bananasBlockPos, LevelReader levelReader, BlockPos targetBlockPos) {
        BlockState blockstate = levelReader.getBlockState(targetBlockPos.relative(bananasBlockPos.getValue(FACING)));
        return blockstate.is(NaughtyMonkeysBlocks.BANANA_STEM.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    @Override
    public boolean isPathfindable(BlockState p_51762_, BlockGetter p_51763_, BlockPos p_51764_, PathComputationType p_51765_) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState bananasBlockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        int bananasBlockAge = bananasBlockState.getValue(AGE);
        switch((Direction)bananasBlockState.getValue(FACING)) {
            case SOUTH:
                return SOUTH_AABB[bananasBlockAge];
            case NORTH:
            default:
                return NORTH_AABB[bananasBlockAge];
            case WEST:
                return WEST_AABB[bananasBlockAge];
            case EAST:
                return EAST_AABB[bananasBlockAge];
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState bananasBlockState, LevelAccessor accessor, BlockPos posA, BlockPos posB) {
        return direction == blockState.getValue(FACING) && !blockState.canSurvive(accessor, posA) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, bananasBlockState, accessor, posA, posB);
    }
}

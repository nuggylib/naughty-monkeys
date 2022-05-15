package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.block.BlockBanana;
import com.nuggylib.naughtymonkeys.common.block.BlockOfBanana;
import com.nuggylib.naughtymonkeys.common.block.BlockOfMonkeyPoo;
import com.nuggylib.naughtymonkeys.common.block.grower.BananaPlantGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Block registry class
 *
 * {@link Blocks}
 */
public class NaughtyMonkeysBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NaughtyMonkeys.ID);

    public static final RegistryObject<Block> BLOCK_OF_MONKEY_POO = BLOCKS.register("block_of_monkey_poo", () -> new BlockOfMonkeyPoo(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 6.0F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BLOCK_OF_BANANA = BLOCKS.register("block_of_banana", () -> new BlockOfBanana(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 6.0F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BANANAS = BLOCKS.register("bananas", () -> new BlockBanana(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BANANA_STEM = BLOCKS.register("banana_stem", () -> log(MaterialColor.WOOD, MaterialColor.PODZOL));
    public static final RegistryObject<Block> BANANA_LEAVES = BLOCKS.register("banana_leaves", () ->  leaves(SoundType.GRASS));
    public static final RegistryObject<Block> BANANA_SAPLING = BLOCKS.register("banana_sapling", () -> new SaplingBlock(new BananaPlantGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    /**
     * Helper method taken from Minecraft's {@link net.minecraft.world.level.block.Blocks} class used to generate log blocks for registration.
     *
     * @param barkColor         The {@link net.minecraft.world.level.material.MaterialColor} to use for the log's bark (outer) faces
     * @param coreColor         The {@link net.minecraft.world.level.material.MaterialColor} to use for the log's core (inner/rings) faces
     * @return                  A {@link net.minecraft.world.level.block.RotatedPillarBlock}
     */
    private static RotatedPillarBlock log(MaterialColor barkColor, MaterialColor coreColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> {
            return blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? barkColor : coreColor;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    /**
     * Static helper used to determine if the given entity is a monkey.
     *
     * This method is used to control what can spawn on the banana leaves
     *
     * @return  true if the given entity is a monkey, otherwise false
     */
    public static Boolean monkey(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == NaughtyMonkeysEntities.MONKEY.get();
    }

    /**
     * Static helper method taken from Minecraft's {@link net.minecraft.world.level.block.Blocks} class used to generate leaves blocks for registration
     */
    private static LeavesBlock leaves(SoundType soundType) {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(NaughtyMonkeysBlocks::monkey).isSuffocating(NaughtyMonkeysBlocks::never).isViewBlocking(NaughtyMonkeysBlocks::never));
    }

    /**
     * Static helper method taken from Minecraft's {@link net.minecraft.world.level.block.Blocks} class - always returns false
     */
    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)false;
    }
    
}

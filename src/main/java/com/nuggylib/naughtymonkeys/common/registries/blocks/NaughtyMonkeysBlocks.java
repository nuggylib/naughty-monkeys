package com.nuggylib.naughtymonkeys.common.registries.blocks;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.block.BlockOfBanana;
import com.nuggylib.naughtymonkeys.common.block.BlockOfMonkeyPoo;
import com.nuggylib.naughtymonkeys.common.registries.entity.NaughtyMonkeysEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Block registry class
 *
 * Inspired by the TwilightForest blocks registry class
 *
 * @see <a href="https://github.com/TeamTwilight/twilightforest/blob/1.18.x/src/main/java/twilightforest/block/TFBlocks.java">TwilightForest Repo - TFBlocks.java</a>
 */
public class NaughtyMonkeysBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NaughtyMonkeys.ID);

    public static final RegistryObject<Block> BLOCK_OF_MONKEY_POO = BLOCKS.register("block_of_monkey_poo", () -> new BlockOfMonkeyPoo(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 6.0F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BLOCK_OF_BANANA = BLOCKS.register("block_of_banana", () -> new BlockOfBanana(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 6.0F).sound(SoundType.SLIME_BLOCK)));

    private static LeavesBlock leaves(SoundType soundType) {
        return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(NaughtyMonkeysBlocks::monkey).isSuffocating(NaughtyMonkeysBlocks::never).isViewBlocking(NaughtyMonkeysBlocks::never));
    }

    private static Boolean monkey(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> entityType) {
        return entityType == NaughtyMonkeysEntities.MONKEY.get();
    }

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }
}

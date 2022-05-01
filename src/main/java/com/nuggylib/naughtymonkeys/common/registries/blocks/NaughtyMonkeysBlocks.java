package com.nuggylib.naughtymonkeys.common.registries.blocks;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

}

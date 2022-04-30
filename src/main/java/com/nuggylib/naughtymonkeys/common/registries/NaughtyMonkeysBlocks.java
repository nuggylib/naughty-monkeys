package com.nuggylib.naughtymonkeys.common.registries;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Block registry class
 *
 * Inspired by the TwilightForest blocks registry class
 *
 * @see <a href="https://github.com/TeamTwilight/twilightforest/blob/1.18.x/src/main/java/twilightforest/block/TFBlocks.java">TwilightForest Repo - TFBlocks.java</a>
 */
public class NaughtyMonkeysBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NaughtyMonkeys.ID);

}

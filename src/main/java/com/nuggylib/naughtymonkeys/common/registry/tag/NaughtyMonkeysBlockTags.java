package com.nuggylib.naughtymonkeys.common.registry.tag;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

/**
 * Block tags for mod
 *
 * @see <a href="https://mcforge.readthedocs.io/en/1.18.x/resources/server/tags/">Minecraft Forge docs - Tags</a>
 */
public class NaughtyMonkeysBlockTags {

    public static final Tag.Named<Block> BANANA_STEM = BlockTags.createOptional(new ResourceLocation(NaughtyMonkeys.ID, "banana_stem"));

}

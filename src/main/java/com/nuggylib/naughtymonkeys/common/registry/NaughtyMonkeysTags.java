package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class NaughtyMonkeysTags {

    public static final Tag.Named<Block> MONKEYS_SPAWNABLE_ON = BlockTags.createOptional(new ResourceLocation("monkeys_spawnable_on", NaughtyMonkeys.ID));

}

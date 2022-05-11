package com.nuggylib.naughtymonkeys.common.tag;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class NaughtyMonkeysTagsProvider extends BlockTagsProvider {

    public NaughtyMonkeysTagsProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.LEAVES).add(NaughtyMonkeysBlocks.BANANA_LEAVES.get());
        this.tag(BlockTags.LOGS).add(NaughtyMonkeysBlocks.BANANA_STEM.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(NaughtyMonkeysBlocks.BANANAS.get());
    }

}

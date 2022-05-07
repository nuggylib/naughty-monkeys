package com.nuggylib.naughtymonkeys.common.tag;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registries.blocks.NaughtyMonkeysBlocks;
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
        try {
            this.tag(BlockTags.LEAVES).add(NaughtyMonkeysBlocks.BANANA_LEAVES.get());
            this.tag(BlockTags.LOGS).add(NaughtyMonkeysBlocks.BANANA_STEM.get());
        } catch (Error error) {
            NaughtyMonkeys.LOGGER.error(error.getMessage());
        }

    }

}

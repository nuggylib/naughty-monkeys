package com.nuggylib.naughtymonkeys.common.tag;

import com.nuggylib.naughtymonkeys.common.registries.NaughtyMonkeysBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
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
    }

}

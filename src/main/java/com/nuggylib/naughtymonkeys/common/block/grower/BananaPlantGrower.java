package com.nuggylib.naughtymonkeys.common.block.grower;

import com.nuggylib.naughtymonkeys.common.registries.feature.NaughtyMonkeysFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BananaPlantGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random p_60014_, boolean hasBeeHive) {
        return NaughtyMonkeysFeatures.BANANA_PLANT;
    }
}

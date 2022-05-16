package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysPlacedFeatures {

    public static PlacedFeature BANANA_PLANT;

    public static void init() {
        BANANA_PLANT = register("banana_tree", NaughtyMonkeysConfiguredFeatures.BANANA_PLANT.placed(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(NaughtyMonkeysBlocks.BANANA_SAPLING.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));

    }

    private static PlacedFeature register(String name, PlacedFeature configuredFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(NaughtyMonkeys.ID, name), configuredFeature);
    }

}

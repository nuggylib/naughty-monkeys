package com.nuggylib.naughtymonkeys.common.registries;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.feature.BananaPlantFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NaughtyMonkeysFoliagePlacers<P extends FoliagePlacer> extends net.minecraftforge.registries.ForgeRegistryEntry<FoliagePlacerType<?>> {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, NaughtyMonkeys.ID);

    public static final RegistryObject<FoliagePlacerType<BananaPlantFoliagePlacer>> BANANA_PLANT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("banana_plant_foliage_placer", () -> new FoliagePlacerType<>(BananaPlantFoliagePlacer.CODEC));

}

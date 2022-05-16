package com.nuggylib.naughtymonkeys.common;

import com.nuggylib.naughtymonkeys.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NaughtyMonkeys.ID)
public class NaughtyMonkeys
{
    public static final String ID = "naughtymonkeys";
    private static final String MODEL_DIR = "textures/model/";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public NaughtyMonkeys() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        NaughtyMonkeysBlocks.BLOCKS.register(modbus);
        NaughtyMonkeysBlocks.VANILLA_BLOCKS.register(modbus);
        NaughtyMonkeysItems.ITEMS.register(modbus);
        NaughtyMonkeysEntities.ENTITIES.register(modbus);
        NaughtyMonkeysEntities.SPAWN_EGGS.register(modbus);
        NaughtyMonkeysEffects.MOB_EFFECTS.register(modbus);
        NaughtyMonkeysFoliagePlacers.FOLIAGE_PLACERS.register(modbus);
        NaughtyMonkeysTreeDecorators.TREE_DECORATOR_TYPES.register(modbus);

    }

    /**
     * Defines the Creative Tab for the Naughty Monkeys mod
     */
    public static final CreativeModeTab TAB_NAUGHTY_MONKEYS = new CreativeModeTab("naughtymonkeys") {
        public @NotNull ItemStack makeIcon() {
            return new ItemStack( NaughtyMonkeysItems.BANANA.get() );
        }
    };

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(ID, name.toLowerCase(Locale.ROOT));
    }

    public static ResourceLocation getModelTexture(String name) {
        return new ResourceLocation(ID, MODEL_DIR + name);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        NaughtyMonkeysConfiguredFeatures.init();
        NaughtyMonkeysPlacedFeatures.init();
    }

    public static void addVegetal(Biome.BiomeCategory category, BiomeGenerationSettingsBuilder gen) {
        List<Supplier<PlacedFeature>> vegetationFeatures = gen.getFeatures(VEGETAL_DECORATION);
        if (category == Biome.BiomeCategory.JUNGLE || category == Biome.BiomeCategory.PLAINS) {
            vegetationFeatures.add(() -> NaughtyMonkeysPlacedFeatures.BANANA_PLANT);
        }
    }

    @SubscribeEvent
    public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> spawns = event.getSpawns().getSpawner(MobCategory.CREATURE);
        // Remove "default" spawn logic inherited for Monkeys
        spawns.removeIf(e -> e.type == NaughtyMonkeysEntities.MONKEY.get());
        // TODO: Figure out how to boost spawn rate for certain areas (or use a spawner block)
        // Make monkeys spawn more *weighted* (just makes it so most, if not all, mobs that spawn are monkeys - not ideal)
        spawns.add(new MobSpawnSettings.SpawnerData(NaughtyMonkeysEntities.MONKEY.get(), 50, 5, 8));

        addVegetal(event.getCategory(), event.getGeneration());
    }

}

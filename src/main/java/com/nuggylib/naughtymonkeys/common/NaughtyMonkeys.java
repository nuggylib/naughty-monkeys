package com.nuggylib.naughtymonkeys.common;

import com.nuggylib.naughtymonkeys.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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

import java.util.Locale;

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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
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

    private void setup(final FMLCommonSetupEvent event)
    {

    }

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

}

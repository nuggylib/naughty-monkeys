package com.nuggylib.naughtymonkeys.common;

import com.nuggylib.naughtymonkeys.common.registries.blocks.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registries.items.NaughtyMonkeysItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NaughtyMonkeys.ID)
public class NaughtyMonkeys
{
    public static final String ID = "naughtymonkeys";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

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

        // TODO: Find out why the blocks aren't registering (asset-related errors have been addressed - there is a code issue somewhere)
        NaughtyMonkeysBlocks.BLOCKS.register(modbus);
        NaughtyMonkeysItems.ITEMS.register(modbus);
//        NaughtyMonkeysItems.ITEMS.register(modbus);
//        NaughtyMonkeysEntities.ENTITIES.register(modbus);
//        NaughtyMonkeysEntities.SPAWN_EGGS.register(modbus);
    }

    /**
     * Defines the Creative Tab for the Naughty Monkeys mod
     */
    public static final CreativeModeTab TAB_NAUGHTY_MONKEYS = new CreativeModeTab("naughtymonkeys") {
        //  TODO: Update with an appropriate icon
        public ItemStack makeIcon() {
            return new ItemStack( Items.LAVA_BUCKET );
        }
    };

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        // InterModComms.sendTo("naughty-monkeys", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
//    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
//    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
//            // register a new block here
//            LOGGER.info("HELLO from Register Block");
//        }
//    }
}

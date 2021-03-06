package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysRenderTypesRegistry {

    @SubscribeEvent
    public static void onRenderTypeSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderType cutout = RenderType.cutout();
            ItemBlockRenderTypes.setRenderLayer(NaughtyMonkeysBlocks.BANANA_SAPLING.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(NaughtyMonkeysBlocks.BANANAS.get(), cutout);
        });
    }

}

package com.nuggylib.naughtymonkeys.client.model;

import com.nuggylib.naughtymonkeys.client.model.entity.ModelMonkey;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysLayerDefinitions {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NaughtyMonkeysModelLayers.MONKEY, ModelMonkey::createBodyLayer);
    }
}

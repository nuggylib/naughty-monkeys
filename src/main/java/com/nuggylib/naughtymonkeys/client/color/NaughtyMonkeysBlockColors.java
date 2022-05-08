package com.nuggylib.naughtymonkeys.client.color;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registries.NaughtyMonkeysBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysBlockColors {

    @SubscribeEvent
    public static void onColorHandlerEvent(final ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        colors.register((blockState, blockAndTintGetter, blockPos, p_92629_) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos) : FoliageColor.getDefaultColor(), NaughtyMonkeysBlocks.BANANA_LEAVES.get());
    }

}

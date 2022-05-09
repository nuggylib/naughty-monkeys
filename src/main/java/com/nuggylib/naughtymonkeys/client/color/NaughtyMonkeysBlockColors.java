package com.nuggylib.naughtymonkeys.client.color;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registries.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registries.NaughtyMonkeysItems;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysBlockColors {

    @SubscribeEvent
    public static void onColorHandlerEvent(final ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();
        itemColors.register((itemStack, p_92688_) -> {
            BlockState blockstate = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, p_92688_);
        }, NaughtyMonkeysItems.BANANA_LEAVES_ITEM.get());
    }

        @SubscribeEvent
    public static void onColorHandlerEvent(final ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        colors.register((blockState, blockAndTintGetter, blockPos, p_92629_) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos) : FoliageColor.getDefaultColor(), NaughtyMonkeysBlocks.BANANA_LEAVES.get());
    }

}

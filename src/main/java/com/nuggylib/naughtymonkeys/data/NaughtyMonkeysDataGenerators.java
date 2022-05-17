package com.nuggylib.naughtymonkeys.data;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.data.loot.NaughtyMonkeysLootTables;
import com.nuggylib.naughtymonkeys.common.tag.NaughtyMonkeysTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysDataGenerators {

    /**
     * Gather data event
     *
     * This event <b>only</b> fires when generating data for the mod. To fire this task, run the <code>runData</code>
     * gradle task.
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new NaughtyMonkeysTagsProvider(generator, NaughtyMonkeys.ID, existingFileHelper));
        generator.addProvider(new NaughtyMonkeysLootTables(generator));
    }
}

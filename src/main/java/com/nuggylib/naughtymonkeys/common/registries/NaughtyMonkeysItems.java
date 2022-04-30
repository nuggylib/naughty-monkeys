package com.nuggylib.naughtymonkeys.common.registries;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Item registry class
 *
 * Inspired by the TwilightForest items registry class
 *
 * @see <a href="https://github.com/TeamTwilight/twilightforest/blob/1.18.x/src/main/java/twilightforest/item/TFItems.java">TwilightForest Repo - TFItems.java</a>
 */
public class NaughtyMonkeysItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NaughtyMonkeys.ID);

}

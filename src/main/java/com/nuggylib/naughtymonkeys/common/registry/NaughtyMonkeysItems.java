package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.item.ItemBanana;
import com.nuggylib.naughtymonkeys.common.item.ItemBananaBunch;
import com.nuggylib.naughtymonkeys.common.item.ItemMonkeyPoo;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Item registry class
 *
 * Inspired by the TwilightForest items registry class
 *
 * @see <a href="https://github.com/TeamTwilight/twilightforest/blob/1.18.x/src/main/java/twilightforest/item/TFItems.java">TwilightForest Repo - TFItems.java</a>
 */
public class NaughtyMonkeysItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NaughtyMonkeys.ID);

    // Blocks
    public static final RegistryObject<BlockItem> BLOCK_OF_MONKEY_POO_ITEM = ITEMS.register("block_of_monkey_poo", () -> new BlockItem(NaughtyMonkeysBlocks.BLOCK_OF_MONKEY_POO.get(), new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<BlockItem> BLOCK_OF_BANANA_ITEM = ITEMS.register("block_of_banana", () -> new BlockItem(NaughtyMonkeysBlocks.BLOCK_OF_BANANA.get(), new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<BlockItem> BANANA_STEM_ITEM = ITEMS.register("banana_stem", () -> new BlockItem(NaughtyMonkeysBlocks.BANANA_STEM.get(), new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<BlockItem> BANANA_LEAVES_ITEM = ITEMS.register("banana_leaves", () -> new BlockItem(NaughtyMonkeysBlocks.BANANA_LEAVES.get(), new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<BlockItem> BANANA_SAPLING_ITEM = ITEMS.register("banana_sapling", () -> new BlockItem(NaughtyMonkeysBlocks.BANANA_SAPLING.get(), new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    // Items
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new ItemBanana(new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<Item> MONKEY_POO = ITEMS.register("monkey_poo", () -> new ItemMonkeyPoo(new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));
    public static final RegistryObject<Item> BANANA_BUNCH = ITEMS.register("banana_bunch", () -> new ItemBananaBunch(new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS)));


    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS);
    }
}

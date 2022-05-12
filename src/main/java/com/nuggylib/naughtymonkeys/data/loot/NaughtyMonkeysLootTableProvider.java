package com.nuggylib.naughtymonkeys.data.loot;

import com.google.gson.GsonBuilder;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.block.BlockBanana;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class NaughtyMonkeysLootTableProvider extends LootTableProvider {

    private static final float[] BANANA_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    private final DataGenerator generator;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public NaughtyMonkeysLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
        this.generator = dataGenerator;
    }

    @Override
    public void run(HashCache cache) {
        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        LootItemCondition.Builder itemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(NaughtyMonkeysBlocks.BANANAS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockBanana.AGE, 3));
        // TODO: Fine tune this so that we don't need redundant fields - this is just reusing vanilla Minecraft code
        tables.put(NaughtyMonkeysBlocks.BANANAS.get().getLootTable(), createCropDrops(NaughtyMonkeysBlocks.BANANAS.get(), NaughtyMonkeysItems.BANANA_BUNCH.get(), NaughtyMonkeysItems.BANANA_BUNCH.get(), itemConditionBuilder).build());
        writeTables(cache, tables);
    }

    private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                NaughtyMonkeys.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    protected static LootTable.Builder createCropDrops(Block block, Item cropItem, Item seedItem, LootItemCondition.Builder builder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(cropItem).when(builder).otherwise(LootItem.lootTableItem(seedItem)))).withPool(LootPool.lootPool().when(builder).add(LootItem.lootTableItem(seedItem).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))));
    }

}

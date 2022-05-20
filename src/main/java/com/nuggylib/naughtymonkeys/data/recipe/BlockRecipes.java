package com.nuggylib.naughtymonkeys.data.recipe;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class BlockRecipes extends RecipeProvider {

    public BlockRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(NaughtyMonkeys.ID, "block/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(NaughtyMonkeysBlocks.BLOCK_OF_MONKEY_POO.get(), 1)
                .pattern("ppp")
                .pattern("ppp")
                .pattern("ppp")
                .define('p', NaughtyMonkeysItems.MONKEY_POO.get())
                .group(NaughtyMonkeys.ID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(NaughtyMonkeysBlocks.BLOCK_OF_MONKEY_POO.get()))
                .save(consumer, saveResource("block_of_monkey_poo"));

        ShapedRecipeBuilder.shaped(NaughtyMonkeysBlocks.BLOCK_OF_BANANA.get(), 1)
                .pattern("bbb")
                .pattern("bbb")
                .pattern("bbb")
                .define('b', NaughtyMonkeysItems.BANANA.get())
                .group(NaughtyMonkeys.ID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(NaughtyMonkeysBlocks.BLOCK_OF_BANANA.get()))
                .save(consumer, saveResource("block_of_banana"));

    }
}

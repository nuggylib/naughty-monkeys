package com.nuggylib.naughtymonkeys.data.recipe;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysBlocks;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ItemRecipes extends RecipeProvider {
    public ItemRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(NaughtyMonkeys.ID, "item/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        // Shaped recipes
        ShapedRecipeBuilder.shaped(NaughtyMonkeysItems.BANANA_HAT.get(), 1)
                .pattern("ppp")
                .pattern("p p")
                .define('p', NaughtyMonkeysItems.BANANA_PEEL.get())
                .group(NaughtyMonkeys.ID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(NaughtyMonkeysItems.BANANA_HAT.get()))
                .save(consumer, saveResource("banana_hat"));

        // Shapeless recipes
        ShapelessRecipeBuilder.shapeless(NaughtyMonkeysItems.BANANA.get(), 1)
                .requires(NaughtyMonkeysItems.BANANA_BUNCH.get())
                .group(NaughtyMonkeys.ID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(NaughtyMonkeysItems.BANANA.get()))
                .save(consumer, "banana_bunch");

        ShapelessRecipeBuilder.shapeless(NaughtyMonkeysItems.BANANA_PEEL.get(), 1)
                .requires(NaughtyMonkeysItems.BANANA.get())
                .group(NaughtyMonkeys.ID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(NaughtyMonkeysItems.BANANA_PEEL.get()))
                .save(consumer, "banana_peel");

        ShapelessRecipeBuilder.shapeless(Items.PAPER, 8)
                .requires(NaughtyMonkeysBlocks.BANANA_STEM.get())
                .group("minecraft")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PAPER))
                .save(consumer);
    }
}

package com.waffleman0310.ancientmagicks.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void registerAllRecipes() {

    }

    // Rework for easier name and group identification

    public static void regsiterShapedRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Object... params) {
        GameRegistry.addShapedRecipe(name, group, output, params);
    }

    public static void registerShapelessRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Ingredient... params) {
        GameRegistry.addShapelessRecipe(name, group, output, params);
    }

    public static void regsiterSmeltingRecipe(ItemStack input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }
}

package com.waffleman0310.ancientmagicks.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public interface IRecipeRegistry<T extends IMachineRecipe> {

	List<T> getRegistry();

	default boolean addRecipe(T recipe) {
		boolean duplicate = getRegistry().stream().anyMatch(r -> r.equals(recipe));

		if (duplicate) {
			return false;
		}

		getRegistry().add(recipe);
		return true;
	}

	default boolean hasRecipe(NonNullList<ItemStack> input) {
		return getRegistry()
				.stream()
				.anyMatch(recipe -> recipe.getInput().containsAll(input));
	};


	default T getRecipe(NonNullList<ItemStack> input) {
		return getRegistry()
				.stream()
				.filter(recipe -> recipe.getInput().containsAll(input))
				.findFirst()
				.get();
	}
}

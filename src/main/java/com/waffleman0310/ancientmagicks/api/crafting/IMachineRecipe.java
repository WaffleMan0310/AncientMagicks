package com.waffleman0310.ancientmagicks.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IMachineRecipe {

	NonNullList<ItemStack> getOutput();

	NonNullList<ItemStack> getInput();

	int getCookTime();

	double getExperience();
}

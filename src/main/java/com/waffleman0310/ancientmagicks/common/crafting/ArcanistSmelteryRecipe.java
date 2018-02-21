package com.waffleman0310.ancientmagicks.common.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ArcanistSmelteryRecipe extends ManaMachineRecipe {

	private final int infusionTime;

	public ArcanistSmelteryRecipe(NonNullList<ItemStack> output, NonNullList<ItemStack> input, int cookTime, int infusionTime, int manaPerTick, double experience) {
		super(output, input, cookTime, manaPerTick, experience);
		this.infusionTime = infusionTime;
	}

	public int getInfusionTime() {
		return infusionTime;
	}


}

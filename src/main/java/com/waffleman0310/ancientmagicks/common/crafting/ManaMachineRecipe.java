package com.waffleman0310.ancientmagicks.common.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ManaMachineRecipe extends MachineRecipe {

	private final int manaPerTick;

	public ManaMachineRecipe(NonNullList<ItemStack> output, NonNullList<ItemStack> input, int cookTime, int manaPerTick, double experience) {
		super(output, input, cookTime, experience);
		this.manaPerTick = manaPerTick;
	}

	public int getManaPerTick() {
		return manaPerTick;
	}
}

package com.waffleman0310.ancientmagicks.common.crafting;

import com.waffleman0310.ancientmagicks.api.crafting.IMachineRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class MachineRecipe implements IMachineRecipe {

	private final NonNullList<ItemStack> output;
	private final NonNullList<ItemStack> input;
	private final int cookTime;
	private final double experience;

	public MachineRecipe(NonNullList<ItemStack> output, NonNullList<ItemStack> input, int cookTime, double experience) {
		this.output = output;
		this.input = input;
		this.cookTime = cookTime;
		this.experience = experience;
	}

	@Override
	public NonNullList<ItemStack> getOutput() {
		return output;
	}

	@Override
	public NonNullList<ItemStack> getInput() {
		return input;
	}

	@Override
	public int getCookTime() {
		return cookTime;
	}

	@Override
	public double getExperience() {
		return experience;
	}
}

package com.waffleman0310.ancientmagicks.common.crafting;

import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SchoolMachineRecipe<T extends School> extends MachineRecipe {

	private final T school;
	private final int resourcePerTick;

	public SchoolMachineRecipe(NonNullList<ItemStack> output, NonNullList<ItemStack> input, int cookTime, T school, int resourcePerTick ,double experience) {
		super(output, input, cookTime, experience);
		this.school = school;
		this.resourcePerTick = resourcePerTick;
	}

	public T getSchool() {
		return school;
	}

	public int getResourcePerTick() {
		return resourcePerTick;
	}
}

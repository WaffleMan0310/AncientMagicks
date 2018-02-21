package com.waffleman0310.ancientmagicks.common.crafting;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Map;

public class ArcanistSmelteryRecipes {

	// Convert the itemstacks to items to avoid NBT conflict when comparing

	private static final ArcanistSmelteryRecipes ARCANIST_SMELTERY_RECIPES = new ArcanistSmelteryRecipes();

	private Map<ItemStack, ItemStack> smelteryList = Maps.newHashMap();
	private Map<ItemStack, NonNullList<ItemStack>> reagentList = Maps.newHashMap();
	private Map<ItemStack, Integer> cookTimeList = Maps.newHashMap();
	private Map<ItemStack, Integer> infusionTimeList = Maps.newHashMap();
	private Map<ItemStack, Long> manaList = Maps.newHashMap();
	Map<ItemStack, Double> experienceList = Maps.newHashMap();

	public static ArcanistSmelteryRecipes instance() {
		return ARCANIST_SMELTERY_RECIPES;
	}

	private ArcanistSmelteryRecipes() {
		//Add recipes here
		NonNullList<ItemStack> TEST_RECIPE_REAGENTS = NonNullList.withSize(3, ItemStack.EMPTY);
		TEST_RECIPE_REAGENTS.set(0, new ItemStack(Blocks.COBBLESTONE, 1, 0));
		TEST_RECIPE_REAGENTS.set(1, new ItemStack(Blocks.COBBLESTONE, 1, 0));
		TEST_RECIPE_REAGENTS.set(2, new ItemStack(Blocks.COBBLESTONE, 1, 0));


		addSmelteryRecipe(
				new ItemStack(Items.DIAMOND, 1, 0),
				new ItemStack(Blocks.DIRT, 1, 0),
				TEST_RECIPE_REAGENTS,
				200,
				200,
				50,
				0.5F
		);
	}


	public void addSmelteryRecipeItem(Item output, ItemStack input, NonNullList<ItemStack> reagents, int cookTime, int infusionTime, long manaPerTick, double experience) {
		addSmelteryRecipe(new ItemStack(output, 1, 0), input, reagents, cookTime, infusionTime, manaPerTick, experience);
	}

	public void addSmelteryRecipeBlock(Block output, ItemStack input, NonNullList<ItemStack> reagents, int cookTime, int infusionTime, long manaPerTick, double experience) {
		addSmelteryRecipeItem(Item.getItemFromBlock(output), input, reagents, cookTime, infusionTime, manaPerTick, experience);
	}

	public void addSmelteryRecipe(ItemStack output, ItemStack input, NonNullList<ItemStack> reagents, int cookTime, int infusionTime, long manaPerTick, double experience) {
		smelteryList.put(input, output);
		reagentList.put(input, reagents);
		cookTimeList.put(input, cookTime);
		infusionTimeList.put(input, infusionTime);
		manaList.put(input, manaPerTick);
		experienceList.put(input, experience);
	}

	public ItemStack getResult(ItemStack stack) {
		for (Map.Entry<ItemStack, ItemStack> entry : smelteryList.entrySet()) {
			return entry.getValue();
		}
		return null;
	}

	public NonNullList<ItemStack> getReagents(ItemStack stack) {
		for (Map.Entry<ItemStack, NonNullList<ItemStack>> entry : reagentList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}

	public double getExperience(ItemStack stack) {
		for (Map.Entry<ItemStack, Double> entry : experienceList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				return entry.getValue();
			}
		}
		return 0.0d;
	}

	public long getManaPerTick(ItemStack stack) {
		for (Map.Entry<ItemStack, Long> entry : manaList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				return entry.getValue();
			}
		}
		return 0L;
	}

	public int getSmeltTime(ItemStack stack) {
		for (Map.Entry<ItemStack, Integer> entry : cookTimeList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				return entry.getValue();
			}
		}
		return 0;
	}

	public int getInfusionTime(ItemStack stack) {
		for (Map.Entry<ItemStack, Integer> entry : infusionTimeList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				return entry.getValue();
			}
		}
		return 0;
	}

	public boolean isRecipe(ItemStack stack) {
		boolean found = false;
		for (Map.Entry<ItemStack, ItemStack> entry : smelteryList.entrySet()) {
			found = stack.isItemEqual(entry.getKey());
		}
		return found;
	}

	public boolean containsAllReagents(ItemStack stack, NonNullList<ItemStack> reagents) {
		int match = 0;
		for (Map.Entry<ItemStack, NonNullList<ItemStack>> entry : reagentList.entrySet()) {
			if (stack.isItemEqual(entry.getKey())) {
				for (int i = 0; i < 3; i++) {
					ItemStack reagent = entry.getValue().get(i);
					for (int j = 0; j < 3; j++) {
						ItemStack pReagent = reagents.get(j);
						if (reagent.isItemEqual(pReagent)) {
							match++;
							i++;
						}
					}
				}
			}
		}
		return match == 3;
	}
}

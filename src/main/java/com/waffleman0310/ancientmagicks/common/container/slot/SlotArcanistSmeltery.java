package com.waffleman0310.ancientmagicks.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotArcanistSmeltery extends Slot {

	public enum Type {
		INPUT, OUTPUT, FUEL, REAGENT, MANA
	}

	private Type type;

	public SlotArcanistSmeltery(IInventory inventoryIn, int index, int xPosition, int yPosition, Type type) {
		super(inventoryIn, index, xPosition, yPosition);
		this.type = type;
	}

	@Override
	protected void onCrafting(ItemStack stack, int amount) {
		super.onCrafting(stack, amount);
	}

	@Override
	protected void onCrafting(ItemStack stack) {
		super.onCrafting(stack);
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		return super.onTake(thePlayer, stack);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		boolean valid = true;

		switch (type) {
			case FUEL:
				// Might give the furnace special fuels, for the time being simply check if the item has a burn time.
				if (TileEntityFurnace.getItemBurnTime(stack) == 0) {
					valid = false;
				}
				break;
			case MANA:
				// Implement
				break;
			case OUTPUT:
				valid = false;
				break;

		}
		return valid;
	}
}

package com.waffleman0310.ancientmagicks.api.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IMachine extends ISidedInventory, ITickable{

	//Predicate<NonNullList<ItemStack>> canCook();

	//Consumer<NonNullList<ItemStack>> consumeFuel();

	//Consumer<NonNullList<ItemStack>> cook();

	NonNullList<ItemStack> getInventory();

	@Override
	default int getSizeInventory() {
		return getInventory().size();
	}

	@Override
	default	boolean isEmpty() {
		return getInventory().isEmpty();
	}

	@Override
	default ItemStack getStackInSlot(int index) {
		return getInventory().get(index);
	}

	@Override
	default ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(getInventory(), index, count);
	}

	@Override
	default ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(getInventory(), index);
	}

	@Override
	default int getInventoryStackLimit() {
		return 64;
	}

	@Override
	default boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	default void openInventory(EntityPlayer player) {

	}

	@Override
	default void closeInventory(EntityPlayer player) {

	}

	@Override
	default void clear() {
		for (int i = 0; i < getSizeInventory(); i++) {
			getInventory().set(i, ItemStack.EMPTY);
		}
	}
}

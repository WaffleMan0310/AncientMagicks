package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.tileentity.IMachine;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class TileEntityMachine extends TileEntity implements IMachine {

	protected NonNullList<ItemStack> inventory;
	private ItemStackHandler inventoryHandler = new ItemStackHandler(inventory);

	private int totalCookTime;
	private int cookTime;

	public TileEntityMachine(int inventorySlots) {
		inventory = NonNullList.withSize(inventorySlots, ItemStack.EMPTY);
	}


	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, inventory);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		ItemStackHelper.saveAllItems(compound, inventory);
		return super.writeToNBT(compound);
	}

	@Override
	public NonNullList<ItemStack> getInventory() {
		return this.inventory;
	}

	@Override
	public String getName() {
		if (getBlockType() instanceof AncientMagicksBlock) {
			return ((AncientMagicksBlock) getBlockType()).getName();
		}
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing));
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) this.inventoryHandler;
		}
		return super.getCapability(capability, facing);
	}
}

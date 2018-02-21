package com.waffleman0310.ancientmagicks.common.tileentity;

import com.waffleman0310.ancientmagicks.api.mana.IMana;
import com.waffleman0310.ancientmagicks.common.crafting.ArcanistSmelteryRecipes;
import com.waffleman0310.ancientmagicks.common.tileentity.base.TileEntityManaMachine;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;

public class TileEntityArcanistSmeltery extends TileEntityManaMachine {

	private int reagentInfusers = 3;
	private int fuelBurnTime;
	private int fuelLeft;
	private int totalSmeltTime;
	private int smeltTime;
	private int infusionTime;
	private int totalInfusionTime;
	private boolean infusionFinished;
	private boolean formed;

	private static BlockPattern pattern;

	public TileEntityArcanistSmeltery() {
		super(7, 1000000, 20, IMana.EnumManaType.NORMAL);
		manaStorage.setManaStored(1000000);
	}

	@Override
	public void update() {
		boolean wasFormed = this.isFormed();
		boolean wasInfusing = this.isInfusing();
		boolean wasBurning = this.isBurning();
		boolean shouldBeDirty = false;

		if (isBurning()) {
			fuelLeft--;
		}

		if (!this.world.isRemote) {
			if (!canInfuseReagents()) {
				this.infusionTime = 0;
				this.totalInfusionTime = 0;
			} else {
				shouldBeDirty = true;
				if (canInfuseReagents()) {
					long manaPerTick = ArcanistSmelteryRecipes.instance().getManaPerTick(getInputSlot());

					consumeMana(manaPerTick);
					this.infusionTime++;

					if (this.infusionTime == this.totalInfusionTime) {
						infuseReagent();

						this.totalInfusionTime = 0;
						this.infusionTime = 0;

						this.totalSmeltTime = ArcanistSmelteryRecipes.instance().getSmeltTime(getInputSlot());
						this.smeltTime = 0;
					}
				}
			}

			if (!canSmelt()) {
				this.smeltTime = 0;
				this.totalSmeltTime = 0;
			} else {
				shouldBeDirty = true;
				if (!isBurning() && !getFuelSlot().isEmpty() && this.fuelLeft == 0) {
					this.fuelBurnTime = getBurnTime(getFuelSlot());
					this.fuelLeft = this.fuelBurnTime;

					getFuelSlot().shrink(1);

					if (getFuelSlot().isEmpty()) {
						setFuelSlot(ItemStack.EMPTY);
					}
				}

				if (isBurning() && canSmelt()) {
					this.smeltTime++;

					if (this.smeltTime == this.totalSmeltTime) {
						smelt();

						this.smeltTime = 0;
						this.infusionTime = 0;
						this.infusionFinished = false;

						if (!getInputSlot().isEmpty() && canInfuseReagents()) {
							this.totalInfusionTime = ArcanistSmelteryRecipes.instance().getInfusionTime(getInputSlot());
							this.totalSmeltTime = ArcanistSmelteryRecipes.instance().getSmeltTime(getInputSlot());
						} else {
							this.totalInfusionTime = 0;
							this.totalSmeltTime = 0;
						}
					}
				}
			}

			if (wasBurning != this.isBurning() || wasInfusing != this.isInfusing() || wasFormed != this.isFormed()) {
				shouldBeDirty = true;
			}
		}

		if (shouldBeDirty) {
			this.markDirty();
			sendManaToClient();
		}
	}

	public boolean canForm() {
		// check for the block pattern that is the multiblock

		return false;
	}

	public void form() {
		// set the formed state on the infusers and the current block
		this.formed = true;
	}

	public int getBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack);
	}

	private boolean canInfuseReagents() { // Shouldn't continue to check if recipe is not found
		// Check if there is an existing recipe with the input slot as well as the 3 reagent slots.
		boolean recipeExists = ArcanistSmelteryRecipes.instance().isRecipe(getInputSlot()) && ArcanistSmelteryRecipes.instance().containsAllReagents(getInputSlot(), getReagentSlots());

		// Check if the output of the smeltery is either empty or contains the item the recipe outputs.;
		boolean outputEmptyOrSame = (getOutputSlot() == ItemStack.EMPTY || getOutputSlot().isItemEqual(ArcanistSmelteryRecipes.instance().getResult(getInputSlot())));

		// Check if enough manaStorage if present for 1 tick, in other words you needs to have enough manaStorage per tick in order to smelt the item.
		boolean manaPresent = getManaStored() >= (ArcanistSmelteryRecipes.instance().getManaPerTick(getInputSlot()) * this.getPurityModifier());

		return (recipeExists && outputEmptyOrSame && manaPresent && !infusionFinished);
	}

	private void infuseReagent() { // Consume Items
		if (canInfuseReagents()) {
			for (int i = 0; i < 3; i++) {
				getReagentSlots().get(i).shrink(1);
				if (getReagentSlots().get(i).isEmpty()) {
					getReagentSlots().set(i, ItemStack.EMPTY);
				}
			}
			this.infusionFinished = true;
		}
	}

	private boolean canSmelt() {
		// Check if the output of the smeltery is either empty or contains the item the recipe outputs. Same as on the infusion check.
		boolean outputEmpty = (getOutputSlot() == ItemStack.EMPTY || getOutputSlot().isItemEqual(ArcanistSmelteryRecipes.instance().getResult(getInputSlot())));
		// Checks to makes sure the input item is still present for the smelting phase.
		boolean inputPresent = ArcanistSmelteryRecipes.instance().isRecipe(getInputSlot());

		return (outputEmpty && inputPresent && this.infusionFinished);
	}

	private void smelt() {
		if (canSmelt()) {
			ItemStack result = ArcanistSmelteryRecipes.instance().getResult(getInputSlot());

			if (getOutputSlot().isEmpty()) {
				setOutputSlot(result);
			} else if (getOutputSlot().isItemEqual(result)) {
				getOutputSlot().grow(1);
			}

			getInputSlot().shrink(1);
		}
	}

	private boolean isFormed() {
		return this.formed;
	}

	private boolean isBurning() {
		return this.fuelLeft > 0;
	}

	private boolean isInfusing() {
		return !this.infusionFinished;
	}

	public void setInputSlot(ItemStack stack) {
		this.inventory.set(2, stack);
	}

	public void setOutputSlot(ItemStack stack) {
		this.inventory.set(3, stack);
	}

	public void setFuelSlot(ItemStack stack) {
		this.inventory.set(0, stack);
	}

	public void setManaSlot(ItemStack stack) {
		this.inventory.set(1, stack);
	}

	public ItemStack getInputSlot() {
		return this.inventory.get(2);
	}

	public ItemStack getOutputSlot() {
		return this.inventory.get(3);
	}

	public ItemStack getFuelSlot() {
		return this.inventory.get(0);
	}

	public ItemStack getManaSlot() {
		return this.inventory.get(1);
	}

	public NonNullList<ItemStack> getReagentSlots() {
		NonNullList<ItemStack> reagents = NonNullList.withSize(3, ItemStack.EMPTY);
		reagents.set(0, this.inventory.get(4));
		reagents.set(1, this.inventory.get(5));
		reagents.set(2, this.inventory.get(6));

		return reagents;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		this.reagentInfusers = compound.getInteger("infusers");
		this.fuelLeft = compound.getInteger("fuelLeft");
		this.smeltTime = compound.getInteger("smeltTime");
		this.infusionTime = compound.getInteger("infusionTime");
		this.fuelBurnTime = compound.getInteger("fuelBurnTime");
		this.totalSmeltTime = compound.getInteger("totalSmeltTime");
		this.totalInfusionTime = compound.getInteger("totalInfusionTime");
		this.infusionFinished = compound.getBoolean("infusionFinished");
		this.formed = compound.getBoolean("formed");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		compound.setInteger("infusers", this.reagentInfusers);
		compound.setInteger("fuelLeft", this.fuelLeft);
		compound.setInteger("smeltTime", this.smeltTime);
		compound.setInteger("infusionTime", this.infusionTime);
		compound.setInteger("fuelBurnTime", this.fuelBurnTime);
		compound.setInteger("totalSmeltTime", this.totalSmeltTime);
		compound.setInteger("totalInfusionTime", this.totalInfusionTime);
		compound.setBoolean("infusionFinished", this.infusionFinished);
		compound.setBoolean("formed", this.formed);

		return super.writeToNBT(compound);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack stackInSlot = inventory.get(index);
		boolean isStackEqualToSlot = !stack.isEmpty() && stackInSlot.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(stackInSlot, stack);
		this.inventory.set(index, stack);

		if ((index == 2 || index == 4 || index == 5 || index == 6) && !isStackEqualToSlot) {
			if (ArcanistSmelteryRecipes.instance().isRecipe(getInputSlot())) {
				if (ArcanistSmelteryRecipes.instance().containsAllReagents(getInputSlot(), getReagentSlots())) {
					this.totalInfusionTime = ArcanistSmelteryRecipes.instance().getInfusionTime(getInputSlot());
					this.infusionTime = 0;

					this.totalSmeltTime = ArcanistSmelteryRecipes.instance().getSmeltTime(getInputSlot());
					this.smeltTime = 0;

					this.infusionFinished = false;
				}
			}
		}

		this.markDirty();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 3) {
			return false;
		}
		return true;
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case 0:
				return fuelLeft;
			case 1:
				return smeltTime;
			case 2:
				return infusionTime;
			case 3:
				return fuelBurnTime;
			case 4:
				return totalSmeltTime;
			case 5:
				return totalInfusionTime;
			case 6:
				int i = 0;
				if (infusionFinished) {
					i = 1;
				}
				return i;
			case 7:
				int j = 0;
				if (formed) {
					j = 1;
				}
				return j;
			case 8:
				return reagentInfusers;
			default:
				return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case 0:
				fuelLeft = value;
				break;
			case 1:
				smeltTime = value;
				break;
			case 2:
				infusionTime = value;
				break;
			case 3:
				fuelBurnTime = value;
				break;
			case 4:
				totalSmeltTime = value;
				break;
			case 5:
				totalInfusionTime = value;
				break;
			case 6:
				if (value > 0) {
					infusionFinished = true;
				} else {
					infusionFinished = false;
				}
				break;
			case 7:
				if (value > 0) {
					formed = true;
				} else {
					formed = false;
				}
			case 8:
				reagentInfusers = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 8;
	}
}

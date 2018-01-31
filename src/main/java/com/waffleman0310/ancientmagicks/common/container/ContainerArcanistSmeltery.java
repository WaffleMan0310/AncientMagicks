package com.waffleman0310.ancientmagicks.common.container;

import com.waffleman0310.ancientmagicks.common.container.base.AncientMagicksContainer;
import com.waffleman0310.ancientmagicks.common.container.slot.SlotArcanistSmeltery;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerArcanistSmeltery extends AncientMagicksContainer {

	public static final int INPUT_SLOT_X = 80;
	public static final int INPUT_SLOT_Y = 35;

	public static final int OUTPUT_SLOT_X = 145;
	public static final int OUTPUT_SLOT_Y = 35;

	public static final int FUEL_SLOT_X = 80;
	public static final int FUEL_SLOT_Y = 87;

	public static final int MANA_SLOT_X = -5;
	public static final int MANA_SLOT_Y = 92;

	public static final int REAGENT_SLOTS_X = 18;
	public static final int REAGENT_SLOTS_Y = -77;
	public static final int REAGENT_SLOT_SPACING = 22;

	public static final int INVENTORY_SLOTS_X = 8;
	public static final int INVENTORY_SLOTS_Y = 125;

	private TileEntityArcanistSmeltery arcanistSmeltery;

	private int fuelBurnTime;
	private int fuelLeft;
	private int totalSmeltTime;
	private int smeltTime;
	private int infusionTime;
	private int totalInfusionTime;
	private boolean infusionFinished;

	public ContainerArcanistSmeltery(IInventory playerInventory, TileEntityArcanistSmeltery arcanistSmeltery) {
		super(playerInventory);
		this.arcanistSmeltery = arcanistSmeltery;

		addPlayerInventory(INVENTORY_SLOTS_X, INVENTORY_SLOTS_Y);

		// Generate Reagent Slots
		for (int i = 4; i < 7; i++) {
			this.addSlotToContainer(
					new SlotArcanistSmeltery(
							arcanistSmeltery,
							i,
							REAGENT_SLOTS_X,
							(i * REAGENT_SLOT_SPACING) + REAGENT_SLOTS_Y,
							SlotArcanistSmeltery.Type.REAGENT
					)
			);
		}

		// Fuel
		this.addSlotToContainer(new SlotArcanistSmeltery(arcanistSmeltery, 0, FUEL_SLOT_X, FUEL_SLOT_Y, SlotArcanistSmeltery.Type.FUEL));
		// Mana
		this.addSlotToContainer(new SlotArcanistSmeltery(arcanistSmeltery, 1, MANA_SLOT_X, MANA_SLOT_Y, SlotArcanistSmeltery.Type.MANA));
		// Input
		this.addSlotToContainer(new SlotArcanistSmeltery(arcanistSmeltery, 2, INPUT_SLOT_X, INPUT_SLOT_Y, SlotArcanistSmeltery.Type.INPUT));
		//Output
		this.addSlotToContainer(new SlotArcanistSmeltery(arcanistSmeltery, 3, OUTPUT_SLOT_X, OUTPUT_SLOT_Y, SlotArcanistSmeltery.Type.OUTPUT));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		TileEntityArcanistSmeltery smeltery = this.arcanistSmeltery;

		for (int i = 0; i < listeners.size(); i++) {
			IContainerListener listener = listeners.get(i);
			if (this.fuelLeft != smeltery.getField(0)) {
				listener.sendProgressBarUpdate(this, 0, smeltery.getField(0));
			}

			if (this.smeltTime != smeltery.getField(1)) {
				listener.sendProgressBarUpdate(this, 1, smeltery.getField(1));
			}

			if (this.infusionTime != smeltery.getField(2)) {
				listener.sendProgressBarUpdate(this, 2, smeltery.getField(2));
			}

			if (this.fuelBurnTime != smeltery.getField(3)) {
				listener.sendProgressBarUpdate(this, 3, smeltery.getField(3));
			}

			if (this.totalSmeltTime != smeltery.getField(4)) {
				listener.sendProgressBarUpdate(this, 4, smeltery.getField(4));
			}

			if (this.totalInfusionTime != smeltery.getField(5)) {
				listener.sendProgressBarUpdate(this, 5, smeltery.getField(5));
			}

			if (this.totalInfusionTime != smeltery.getField(6)) {
				listener.sendProgressBarUpdate(this, 6, smeltery.getField(6));
			}
		}

		this.fuelLeft = smeltery.getField(0);
		this.smeltTime = smeltery.getField(1);
		this.infusionTime = smeltery.getField(2);
		this.fuelBurnTime = smeltery.getField(3);
		this.totalSmeltTime = smeltery.getField(4);
		this.totalInfusionTime = smeltery.getField(5);
		this.infusionFinished = smeltery.getField(6) > 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		this.arcanistSmeltery.setField(id, data);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		// Code...
		return super.transferStackInSlot(playerIn, index);
	}

	@Override
	public boolean getCanCraft(EntityPlayer player) {
		return super.getCanCraft(player);
	}

	@Override
	public void setCanCraft(EntityPlayer player, boolean canCraft) {
		super.setCanCraft(player, canCraft);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return arcanistSmeltery.isUsableByPlayer(playerIn);
	}
}

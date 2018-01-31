package com.waffleman0310.ancientmagicks.common.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public abstract class AncientMagicksContainer extends Container {

	public static final int INVENTORY_SLOT_SPACING = 18;
	public static final int INVENTORY_HAND_SPACING = 3;

	private IInventory playerInventory;

	public AncientMagicksContainer(IInventory playerInventory) {
		this.playerInventory = playerInventory;
	}

	public void addPlayerInventory(int x, int y) {
		// Player Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(
						new Slot(
								playerInventory,
								((i * 9) + j) + 9,
								(j * INVENTORY_SLOT_SPACING) + x,
								(i * INVENTORY_SLOT_SPACING) + y
						)
				);
			}
		}

		// Player Hand
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(
					new Slot(
							playerInventory,
							i,
							(i * INVENTORY_SLOT_SPACING) + x,
							y + (3 * INVENTORY_SLOT_SPACING) + INVENTORY_HAND_SPACING
					)
			);
		}
	}

}

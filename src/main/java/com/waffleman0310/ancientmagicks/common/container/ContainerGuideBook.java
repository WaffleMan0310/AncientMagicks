package com.waffleman0310.ancientmagicks.common.container;

import com.waffleman0310.ancientmagicks.common.container.base.AncientMagicksContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerGuideBook extends AncientMagicksContainer {

	public ContainerGuideBook(IInventory playerInventory) {
		super(playerInventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}

package com.waffleman0310.ancientmagicks.client.gui.guidebook;

import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;


public class GuiGuideBook extends Gui {

	private IPlayerResearch playerResearch;

	public GuiGuideBook(EntityPlayer player) {
		this.playerResearch = player.getCapability(CapabilityResearch.RESEARCH, null);
	}


}

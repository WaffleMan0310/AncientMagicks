package com.waffleman0310.ancientmagicks.api.event;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ResearchUnlockedEvent extends PlayerEvent {

	private IResearchEntry researchUnlocked;

	public ResearchUnlockedEvent(EntityPlayer player) {
		super(player);
	}
}

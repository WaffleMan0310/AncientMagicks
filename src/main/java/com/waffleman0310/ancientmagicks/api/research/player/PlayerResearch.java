package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.registry.*;
import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public class PlayerResearch implements IPlayerResearch {

	protected final HashMap<ResearchNode<IResearchEntry>, Boolean> unlockMap;

	public PlayerResearch() {
		this.unlockMap = new HashMap<>();
		IForgeRegistry<School> schoolRegistry = GameRegistry.findRegistry(School.class);
		schoolRegistry
				.forEach(school -> school.getResearchRegistry()
						.forEach(node -> unlockMap.put(node, false)));
	}

	@Override
	public void unlock(IResearchEntry research) {
		this.unlockMap.replace(getFromMap(research), true);
	}

	@Override
	public boolean canUnlock(IResearchEntry research) {
		boolean prerequisitesUnlocked = true;

		ResearchNode<IResearchEntry> node = getFromMap(research);

		if (node != null) {

			for (ResearchNode<IResearchEntry> prereq : node.getPrerequsites()) {
				prerequisitesUnlocked = this.unlockMap.get(prereq) && prerequisitesUnlocked;
			}
		}
		return false;
	}

	@Override
	public boolean isUnlocked(IResearchEntry research) {
		return this.unlockMap.get(getFromMap(research));
	}

	private ResearchNode<IResearchEntry> getFromMap(IResearchEntry research) {
		ResearchNode<IResearchEntry> match = null;

		for (ResearchNode<IResearchEntry> node : this.unlockMap.keySet()) {
			if (node.getResearch().equals(research)) {
				match = node;
			}
		}

		return match;
	}
}

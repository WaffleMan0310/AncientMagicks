package com.waffleman0310.ancientmagicks.api.research.player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.waffleman0310.ancientmagicks.api.research.registry.*;
import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class PlayerResearch implements IPlayerResearch {

	protected final BiMap<ResearchNode<IResearchEntry>, Boolean> unlockMap = HashBiMap.create();

	public PlayerResearch() {
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

	@Override
	public BiMap<ResearchNode<IResearchEntry>, Boolean> getUnlockedMap() {
		return this.unlockMap;
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

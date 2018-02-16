package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistry;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntryUnlockable;

import java.util.HashMap;

public class PlayerResearch implements IPlayerResearch {

	// want to use School here so people can register their own schools
	protected HashMap<EnumSchool, ResearchRegistry<IResearchEntryUnlockable>> researchLists;

	public PlayerResearch() {
		this.researchLists = new HashMap<>(EnumSchool.values().length);

		// Go through all of the research lists of all the schools and create a unlockable variant
		for(EnumSchool school : EnumSchool.values()) {
			// TODO: new research list with a root thats null is not correct
			ResearchRegistry<IResearchEntryUnlockable> schoolResearch = \new ResearchRegistry<>(null);
			school.get().getResearchList()
					.forEach((node) -> {
						IResearchEntryUnlockable research = (IResearchEntryUnlockable) node.getResearch();
						IResearchEntryUnlockable[] prerequisites = new IResearchEntryUnlockable[node.getPrerequsites().size()];
						for (int i = 0; i < prerequisites.length; i++) {
							prerequisites[i] = (IResearchEntryUnlockable) node.getPrerequsites().get(i).getResearch();
						}

						schoolResearch.register(research, prerequisites);

					});
			this.researchLists.put(school, schoolResearch);
		}
	}

	@Override
	public void unlock(EnumSchool school, IResearchEntryUnlockable research) {
		if (canUnlock(school, research)) {
			this.getResearchList(school).getResearchEntry(research).getResearch().unlock();
		}
	}

	@Override
	public boolean canUnlock(EnumSchool school, IResearchEntryUnlockable research) {
		return this.getResearchList(school).getResearchEntry(research).getPrerequsites()
				.stream()
				.allMatch(node -> node.getResearch().isUnlocked());
	}

	@Override
	public boolean isUnlocked(EnumSchool school, IResearchEntryUnlockable research) {
		return this.getResearchList(school).getResearchEntry(research).getResearch().isUnlocked();
	}

	@Override
	public ResearchRegistry<IResearchEntryUnlockable> getResearchList(EnumSchool school) {
		return this.researchLists.get(school);
	}

	@Override
	public HashMap<EnumSchool, ResearchRegistry<IResearchEntryUnlockable>> getMasterList() {
		return this.researchLists;
	}
}

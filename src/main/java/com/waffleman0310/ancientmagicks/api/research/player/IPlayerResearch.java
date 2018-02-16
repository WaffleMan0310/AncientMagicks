package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistry;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntryUnlockable;

import java.util.HashMap;

public interface IPlayerResearch {

	void unlock(EnumSchool school, IResearchEntryUnlockable research);

	boolean canUnlock(EnumSchool school, IResearchEntryUnlockable research);

	boolean isUnlocked(EnumSchool school, IResearchEntryUnlockable research);

	ResearchRegistry<IResearchEntryUnlockable> getResearchList(EnumSchool school);

	HashMap<EnumSchool, ResearchRegistry<IResearchEntryUnlockable>> getMasterList();
}

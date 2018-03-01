package com.waffleman0310.ancientmagicks.api.research.player;

import com.google.common.collect.BiMap;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchNode;

import java.util.HashMap;

public interface IPlayerResearch {

	void unlock(IResearchEntry research);

	boolean canUnlock(IResearchEntry research);

	boolean isUnlocked(IResearchEntry research);
}

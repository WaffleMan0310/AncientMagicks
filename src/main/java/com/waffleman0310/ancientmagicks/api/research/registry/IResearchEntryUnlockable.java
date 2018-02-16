package com.waffleman0310.ancientmagicks.api.research.registry;

public interface IResearchEntryUnlockable extends IResearchEntry {

	void isVisible();

	void canUnlock();

	void unlock();
}

package com.waffleman0310.ancientmagicks.api.research.registry;

public interface IResearchRegistry<K extends IResearchEntry> {

	void register(K research, K... prerequsites);

	ResearchNode getEntryForID(int id);

	ResearchNode getEntryForResearch(K reseach);

	boolean contains(K research);

	int size();

	boolean isEmpty();

	interface CreateCallback<K extends IResearchEntry> {
		void onCreate(IResearchRegistry<K> registryCreated);
	}

	interface AddCallback<K extends IResearchEntry> {
		void onAdd(K entryToAdd);
	}
}

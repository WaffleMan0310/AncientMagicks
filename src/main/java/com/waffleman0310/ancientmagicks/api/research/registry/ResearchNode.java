package com.waffleman0310.ancientmagicks.api.research.registry;

import java.util.ArrayList;
import java.util.List;

public class ResearchNode<T extends IResearchEntry> {

	private final ArrayList<ResearchNode<T>> prerequisites;
	private final ArrayList<ResearchNode<T>> attributables;

	private final T research;

	public ResearchNode(T entry) {
		this.research = entry;

		prerequisites = new ArrayList<>();
		attributables = new ArrayList<>();
	}

	protected void addPrerequisite(ResearchNode<T> entry) {
		this.prerequisites.add(entry);
	}

	protected void addAttributable(ResearchNode<T> entry) {
		this.attributables.add(entry);
	}

	public List<ResearchNode<T>> getPrerequsites() {
		return this.prerequisites;
	}

	public List<ResearchNode<T>> getAttributables() {
		return this.attributables;
	}

	public T getResearch() {
		return this.research;
	}
}

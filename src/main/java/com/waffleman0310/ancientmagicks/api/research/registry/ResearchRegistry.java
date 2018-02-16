package com.waffleman0310.ancientmagicks.api.research.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ResearchRegistry<K extends IResearchEntry> implements IResearchRegistry<K>{

	private final ResearchNode<K> root = null;
	private final BiMap<Integer, ResearchNode<K>> idMap = HashBiMap.create();
	private final Class<K> entryType;
	private final CreateCallback<K> create;
	private final AddCallback<K> add;
	private final int max;
	private final int min;

	private int nextId = 0;
	private int size;

	public ResearchRegistry(Class<K> type, int min, int max, @Nullable CreateCallback<K> create, @Nullable AddCallback<K> add) {
		this.entryType = type;
		this.create = create;
		this.add = add;
		this.min = min;
		this.max = max;
		this.nextId = min;

		if (create != null) {
			create.onCreate(this);
		}
	}

	@Override
	public void register(K research, K... prerequsites) {
		ResearchNode<K> newNode = new ResearchNode<>(research);

		/*
		check if root is null, if so

		check for duplicates

		check if there is an availible id

		save research without prerequisites until after all are registered, upon which check for prereqs again and see if it will work
		this allows for un ordered registration of research

		also add the research to the bi map
		 */

		boolean hasPrerequisites = true;

		for (K r : prerequsites) {
			hasPrerequisites = this.contains(r) && hasPrerequisites;
		}

		if (!hasPrerequisites) {
			// prerequisites not found, research not added
			return;
		} else {
			idMap.put(nextId, newNode);

			for (K r : prerequsites) {
				ResearchNode<K> prereqisite = getResearchEntry(r);
				newNode.addPrerequisite(prereqisite);
			}

			for (K r : prerequsites) {
				ResearchNode<K> prerequisite = getResearchEntry(r);
				prerequisite.addAttributable(newNode);
			}
		}

		//fire callbacks for when research is added

		nextId++;
	}

	@Override
	public boolean isEmpty() {
		return root.getAttributables().isEmpty();
	}

	@Override
	public boolean contains(K research) {
		if (this.isEmpty()) {
			return getResearchEntry(research) != null;
		}

		return false;
	}

	public int size() {
		this.size = 0;
		forEach((researchNode -> this.size++));
		return size;
	}

	// new name
	public ResearchNode<K> getResearchEntry(K research) {
		return match(node -> node.getResearch().equals(research));
	}

	public ResearchNode<K> getResearchEntry(ResearchNode<K> research) {
		return match(node -> node.equals(research));
	}

	public void forEach(Consumer<ResearchNode<K>> consumer) {
		forEachInternal(this.root, consumer);
	}

	public ResearchNode<K> match(Predicate<ResearchNode<K>> predicate) {
		return matchInternal(this.root, predicate);
	}

	private ResearchNode<K> matchInternal(ResearchNode<K> currentNode, Predicate<ResearchNode<K>> predicate) {
		if (predicate.test(currentNode)) {
			return currentNode;
		} else if (currentNode.getAttributables().isEmpty()) {
			// Nothing found
			return null;
		} else {
			for (ResearchNode<K> node : currentNode.getAttributables()) {
				return matchInternal(node, predicate);
			}
		}

		return null; // why ide?
	}

	private void forEachInternal(ResearchNode<K> currentNode, Consumer<ResearchNode<K>> consumer) {
		consumer.accept(currentNode);
		if (!currentNode.getAttributables().isEmpty()) {
			for (ResearchNode<K> researchNode : currentNode.getAttributables()) {
				forEachInternal(researchNode, consumer);
			}
		}
	}

	private class ResearchNode<T extends IResearchEntry> {

		private final ArrayList<ResearchNode<T>> prerequisites;
		private final ArrayList<ResearchNode<T>> attributables;

		private final T research;

		public ResearchNode(T entry) {
			this.research = entry;

			prerequisites = new ArrayList<>();
			attributables = new ArrayList<>();
		}

		void addPrerequisite(ResearchNode<T> entry) {
			this.prerequisites.add(entry);
		}

		void addAttributable(ResearchNode<T> entry) {
			this.attributables.add(entry);
		}

		List<ResearchNode<T>> getPrerequsites() {
			return this.prerequisites;
		}

		List<ResearchNode<T>> getAttributables() {
			return this.attributables;
		}

		T getResearch() {
			return this.research;
		}
	}
}

package com.waffleman0310.ancientmagicks.api.research.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ResearchRegistry<K extends IResearchEntry> implements IResearchRegistry<K>{

	// system for creating the root node?
	private final ResearchNode<K> root = new ResearchNode<>(null);
	private final BiMap<Integer, ResearchNode<K>> idMap = HashBiMap.create();
	private final Class<K> entryType;
	private final CreateCallback<K> create;
	private final AddCallback<K> add;
	private final int max;
	private final int min;

	private int nextId = 0;

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
		save research without prerequisites until after all are registered, upon which check for prereqs again and see if it will work
		this allows for un ordered registration of research
		 */

		boolean hasId = this.nextId >= max;
		boolean duplicate = getEntry(research) != null;

		for (K r : prerequsites) {
			if (this.contains(r)) {
				newNode.addPrerequisite(getEntry(r));
			} else {
				// what to do if the prereq is not found
			}
		}

		if (hasId & !duplicate) {
			idMap.put(nextId, newNode);

			for (K r : prerequsites) {
				getEntry(r).addAttributable(newNode);
			}

			add.onAdd(research);

			nextId++;
		}
	}

	@Override
	public boolean isEmpty() {
		return root.getAttributables().isEmpty();
	}

	@Override
	public boolean contains(K research) {
		if (this.isEmpty()) {
			return getEntry(research) != null;
		}

		return false;
	}


	@Override
	public ResearchNode<K> getEntry(K research) {
		return match(node -> node.getResearch().equals(research));
	}


	public int size() {
		return this.nextId;
	}

	public ResearchNode getEntry(int id) {
		return this.idMap.get(id);
	}

	public int getID(K research) {
		return this.idMap.inverse().get(this.getEntry(research));
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
		} else {
			for (ResearchNode<K> node : currentNode.getAttributables()) {
				return matchInternal(node, predicate);
			}
		}

		return null;
	}

	private void forEachInternal(ResearchNode<K> currentNode, Consumer<ResearchNode<K>> consumer) {
		consumer.accept(currentNode);
		if (!currentNode.getAttributables().isEmpty()) {
			for (ResearchNode<K> researchNode : currentNode.getAttributables()) {
				forEachInternal(researchNode, consumer);
			}
		}
	}
}

package com.waffleman0310.ancientmagicks.api.research.registry;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchRegistry.AddCallback;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchRegistry.CreateCallback;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ResearchRegistryBuilder<K extends IResearchEntry> {

	private Class<K> type;
	private ResourceLocation name;
	private int maxId = Integer.MAX_VALUE - 1;
	private int minId = 0;
	private List<CreateCallback<K>> createList;
	private List<AddCallback<K>> addList;

	public ResearchRegistryBuilder() {
		createList = new ArrayList<>();
		addList = new ArrayList<>();
	}

	public ResearchRegistryBuilder<K> setName(ResourceLocation name) {
		this.name = name;
		return this;
	}

	public ResearchRegistryBuilder<K> setType(Class<K> type) {
		this.type = type;
		return this;
	}

	public ResearchRegistryBuilder<K> setIDRange(int min, int max) {
		this.minId = min;
		this.maxId = max;
		return this;
	}

	public ResearchRegistryBuilder<K> addCreateCallback(CreateCallback<K> callback) {
		this.createList.add(callback);
		return this;
	}

	public ResearchRegistry<K> create() {
		return new ResearchRegistry<>(type, minId, maxId, null, null);
	}

	public CreateCallback<K> addCreateCallbacks() {
		return registryCreated -> {
			for (CreateCallback<K> callback : this.createList) {
				callback.onCreate(registryCreated);
			}
		};
	}

	public AddCallback<K> addAddCallbacks() {
		return entryToAdd -> {
			for (AddCallback<K> callback : this.addList) {
				callback.onAdd(entryToAdd);
			}
		};
	}

}

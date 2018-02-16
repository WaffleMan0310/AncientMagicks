package com.waffleman0310.ancientmagicks.api.research.registry;

import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

public interface IResearchEntry extends IStringSerializable, INBTSerializable<NBTTagCompound>{

	enum NodeType {
		INFO, ITEM
	}

	enum NodeRarity { // WoW Style
		COMMON, UNCOMMON, RARE, EPIC, LEGENDARY
	}

	IResearchEntry setRegistryName(ResourceLocation name);

	ResourceLocation getRegistryName();

	NodeType getNodeType();

	NodeRarity getRarity();

	School getSchool();

	Item getItem();

	int getMetadata();

	abstract class DefaultImplementation implements IResearchEntry {

		ResourceLocation name = null;

		@Override
		public IResearchEntry setRegistryName(ResourceLocation name) {
			// forge registry checks to see if the resourcelocation was already registered.
			this.name = name;
			return this;
		}

		@Override
		public ResourceLocation getRegistryName() {
			return this.name;
		}
	}
}

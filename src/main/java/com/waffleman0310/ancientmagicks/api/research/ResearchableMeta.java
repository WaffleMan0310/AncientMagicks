package com.waffleman0310.ancientmagicks.api.research;

import com.waffleman0310.ancientmagicks.api.research.IResearchable.NodeType;
import com.waffleman0310.ancientmagicks.schools.EnumSchool;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public final class ResearchableMeta implements IStringSerializable {
	private IResearchable researchableItem;
	private int metadata;

	protected ResearchableMeta(IResearchable researchableItem, int metadata) {
		this.researchableItem = researchableItem;
		this.metadata = metadata;
	}

	public ItemStack getAsStack() {
		return new ItemStack(this.researchableItem.getItem(), 0, getMetadata());
	}

	public NodeType getNodeType() {
		return this.researchableItem.getNodeType(getAsStack());
	}

	public EnumSchool getSchool() {
		return this.researchableItem.getSchool(getAsStack());
	}

	public int getKnowledgeLevels() {
		return this.researchableItem.getKnowledgeLevels(getAsStack());
	}

	public int getMetadata() {
		return this.metadata;
	}

	public String getUnlocalizedName() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.RESEARCH,
				String.format("%s:%d", this.getName(), this.metadata)
		);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ResearchableMeta && ((ResearchableMeta) obj).getName().equals(this.getName());
	}

	@Override
	public String getName() {
		return String.format("research:%s@%d", getAsStack().getItem().getRegistryName(), getMetadata());
	}
}

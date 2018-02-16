package com.waffleman0310.ancientmagicks.api.school;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistryBuilder;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class School extends IForgeRegistryEntry.Impl<School> implements IStringSerializable {

	public final ResearchRegistry<IResearchEntry> REGISTRY;

	public School(String name) {
		setRegistryName(AncientMagicksUtil.modId, name);

		ResearchRegistryBuilder<IResearchEntry> builder = new ResearchRegistryBuilder<>();

		REGISTRY = builder.setName(getRegistryName())
				.setType(IResearchEntry.class)
				.create();
	}

	public abstract String getResourceName();

	public abstract School[] getConnectedSchools();


	public String getUnlocalizedResourceName() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.RESOURCE,
				getResourceName());
	}

	public String getUnlocalizedName() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
				getRegistryName().getResourcePath());
	}

	public String getUnlocalizedDescription() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
				getRegistryName().getResourcePath());
	}
}


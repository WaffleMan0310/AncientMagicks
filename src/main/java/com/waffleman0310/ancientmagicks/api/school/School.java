package com.waffleman0310.ancientmagicks.api.school;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistryBuilder;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiResearchPane.EnumConnectType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class School extends IForgeRegistryEntry.Impl<School> implements IStringSerializable {

	public static final ResourceLocation SCHOOL_ICONS = AncientMagicksUtil.getModResource("textures/gui/school/icons.png");

	private final ResearchRegistry<IResearchEntry> registry;

	private String name;

	public School(String name) {
		this.name = name;

		setRegistryName(AncientMagicksUtil.modId, name);

		ResearchRegistryBuilder<IResearchEntry> builder = new ResearchRegistryBuilder<>();

		registry = builder
				.setName(getRegistryName())
				.setType(IResearchEntry.class)
				.create();
	}

	public abstract String getResourceName();

	public abstract School[] getConnectedSchools();

	public abstract Texture getIconTexture();

	public abstract Texture getNodeTexture();

	public abstract Texture getResearchPaneBackground();

	public abstract Texture getResearchNodeConnectingTexture();

	public abstract EnumConnectType getResearchNodeConnectType();

	public ResearchRegistry<IResearchEntry> getResearchRegistry() {
		return this.registry;
	}

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

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof School && ((School) obj).getRegistryName().equals(this.getRegistryName());
	}
}


package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiResearchPane.EnumConnectType;

public class SchoolMetamorphosis extends School {

	public SchoolMetamorphosis(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "metamorphosis";
	}

	@Override
	public String getResourceName() {
		return "runic_power";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}

	@Override
	public Texture getIconTexture() {
		return new Texture(
				SCHOOL_ICONS,
				224, 0,
				32, 32
		);
	}

	@Override
	public Texture getNodeTexture() {
		return null;
	}

	@Override
	public Texture getResearchPaneBackground() {
		return null;
	}

	@Override
	public Texture getResearchNodeConnectingTexture() {
		return null;
	}

	@Override
	public EnumConnectType getResearchNodeConnectType() {
		return null;
	}
}

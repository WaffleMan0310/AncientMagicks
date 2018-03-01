package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGui;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGuiButton;

public class GuiResearchNode extends AncientMagicksGuiButton {

	private IResearchEntry research;

	public GuiResearchNode(AncientMagicksGui parent, IResearchEntry research, int buttonId, int x, int y) {
		super(parent, buttonId, x, y, 0, 0, research.getName());
		this.research = research;
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialTicks) {

	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}
}

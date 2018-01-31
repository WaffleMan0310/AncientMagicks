package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGuiElement;

public class GuiResearchNode extends AncientMagicksGuiElement {

	// Add textures

	private ResearchableMeta research;

	public GuiResearchNode(ResearchableMeta research, int x, int y) {
		super(x, y);
		this.research = research;
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialTicks) {
		super.drawForground(mouseX, mouseY, partialTicks);
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {
		super.drawBackground(mouseX, mouseY, partialTicks);
	}
}

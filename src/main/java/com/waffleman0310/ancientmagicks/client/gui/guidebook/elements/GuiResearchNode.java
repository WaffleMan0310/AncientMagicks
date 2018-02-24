package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGuiElement;

public class GuiResearchNode extends AncientMagicksGuiElement {

	private IResearchEntry research;

	public GuiResearchNode(IResearchEntry research, int x, int y) {
		super(x, y);
		this.research = research;
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialTicks) {

	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}

	public static void drawConnectingLine(int x1, int y1, int x2, int y2) {

	}
}

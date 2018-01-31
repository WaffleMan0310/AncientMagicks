package com.waffleman0310.ancientmagicks.client.gui.base;

import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public abstract class AncientMagicksGui extends GuiScreen {

	private List<AncientMagicksGuiElement> elements;

	public AncientMagicksGui() {
		this.elements = new ArrayList<>();
	}

	public void drawForground(int mouseX, int mouseY, float partialticks) {

	}

	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}

	public void addElement(AncientMagicksGuiElement element) {
		this.elements.add(element);
	}

	public void removeElement(AncientMagicksGuiElement element) {
		this.elements.removeIf(e -> e.equals(element));
	}

	public List<AncientMagicksGuiElement> getElements() {
		return this.elements;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawBackground(mouseX, mouseY, partialTicks);
		drawForground(mouseX, mouseY, partialTicks);
		if (!this.elements.isEmpty()) {
			elements.forEach((element -> element.drawElement(mouseX, mouseY, partialTicks)));
		}
	}
}

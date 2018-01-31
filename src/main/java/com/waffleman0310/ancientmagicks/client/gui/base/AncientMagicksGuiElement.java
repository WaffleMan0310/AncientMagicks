package com.waffleman0310.ancientmagicks.client.gui.base;

public abstract class AncientMagicksGuiElement extends AncientMagicksGui {

	private int xCoord;
	private int yCoord;

	public AncientMagicksGuiElement(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	public void drawForground(int mouseX, int mouseY, float partialTicks) {

	}

	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}

	public final void drawElement(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(mouseX, mouseY, partialTicks);
		this.drawForground(mouseX, mouseY, partialTicks);
	}
}

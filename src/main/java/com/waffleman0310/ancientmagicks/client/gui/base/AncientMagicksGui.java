package com.waffleman0310.ancientmagicks.client.gui.base;

import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.PositionModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.RotationModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AncientMagicksGui extends GuiScreen {

	public enum EnumDirection {
		UP, DOWN, LEFT, RIGHT
	}

	private final List<AncientMagicksGuiElement> elements;

	public AncientMagicksGui() {
		this.elements = new ArrayList<>();
		this.mc = Minecraft.getMinecraft();
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
		if (!this.elements.isEmpty()) {
			elements.forEach((element -> element.drawElement(mouseX, mouseY, partialTicks)));
		}
		drawBackground(mouseX, mouseY, partialTicks);
		drawForground(mouseX, mouseY, partialTicks);
	}

}

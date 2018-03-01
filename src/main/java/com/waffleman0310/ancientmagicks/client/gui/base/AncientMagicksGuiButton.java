package com.waffleman0310.ancientmagicks.client.gui.base;

import com.waffleman0310.ancientmagicks.api.util.helpers.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AncientMagicksGuiButton extends GuiButton {

	public AncientMagicksGui parent;

	public AncientMagicksGuiButton(AncientMagicksGui parent, int buttonId, int x, int y, int width, int height, String name) {
		super(buttonId, x, y, width, height, name);

		this.parent = parent;
	}

	protected abstract void drawForground(int mouseX, int mouseY, float partialTicks);

	protected abstract void drawBackground(int mouseX, int mouseY, float partialTicks);

	public void drawButton(int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			this.hovered = GuiHelper.isMouseInBounds(this.parent, mouseX, mouseY, this.xPosition, this.yPosition, this.width, this.height);
			this.drawBackground(mouseX, mouseY, partialTicks);
			this.drawForground(mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public void func_191745_a(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
		this.drawButton(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
		return this.visible && this.enabled && GuiHelper.isMouseInBounds(this.parent, mouseX, mouseY, this.xPosition, this.yPosition, this.width, this.height);
	}
}

package com.waffleman0310.ancientmagicks.client.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public abstract class AncientMagicksGui extends GuiScreen {
	
	public AncientMagicksGui() {
		this.mc = Minecraft.getMinecraft();
	}

	public void drawForground(int mouseX, int mouseY, float partialticks) {

	}

	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.enableBlend();

		drawBackground(mouseX, mouseY, partialTicks);

		this.buttonList.forEach(button -> button.func_191745_a(this.mc, mouseX, mouseY, partialTicks));

		drawForground(mouseX, mouseY, partialTicks);

		GlStateManager.disableBlend();
	}
}

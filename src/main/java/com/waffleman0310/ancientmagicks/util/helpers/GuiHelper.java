package com.waffleman0310.ancientmagicks.util.helpers;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;

public class GuiHelper {

	public enum EnumDirection {
		UP, DOWN, LEFT, RIGHT
	}

	public static boolean isMouseInBoundsCenter(GuiContainer container, int mouseX, int mouseY, int x, int y, int w, int h) {
		int centerX = container.width / 2;
		int centerY = container.height / 2;

		int cornerX = centerX - (w / 2) + x;
		int cornerY = centerY - (h / 2) + y;

		return (mouseX >= cornerX) && (mouseY >= cornerY) && (mouseX <= (cornerX + w)) && (mouseY <= (cornerY + h));
	}

	public static void drawCenteredTextureWithOffset(GuiContainer container, int u, int v, int w, int h, int offsetX, int offsetY) {
		int centerX = container.width / 2;
		int centerY = container.height / 2;

		container.drawTexturedModalRect(
				(centerX - (w / 2)) + offsetX,
				(centerY - (h / 2)) + offsetY,
				u,
				v,
				w,
				h
		);
	}

	public static void drawCenteredTextureWithOffsetAndRotation(GuiContainer container, int u, int v, int w, int h, int offsetX, int offsetY, float angle) {
		int centerX = container.width / 2;
		int centerY = container.height / 2;

		GlStateManager.pushMatrix();

		GlStateManager.translate((centerX) + offsetX, (centerY) + offsetY, 0);
		GlStateManager.rotate(angle, 0, 0, 1);

		container.drawTexturedModalRect(
				-(w / 2),
				-(h / 2),
				u,
				v,
				w,
				h
		);

		GlStateManager.translate(0, 0, 0);

		GlStateManager.popMatrix();
	}

	public static void drawCenteredScaledTextureWithOffset(GuiContainer container, int u, int v, int w, int h, int offsetX, int offsetY, EnumDirection dir, float val) {
		int centerX = container.width / 2;
		int centerY = container.height / 2;

		switch (dir) {
			case UP:
				container.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX),
						((centerY - (h / 2)) + offsetY) + (h - (int) (h * val)),
						u,
						v,
						w,
						(int) (h * val)
				);
				break;
			case DOWN:
				container.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX),
						((centerY - (h / 2)) + offsetY),
						u,
						v,
						w,
						(int) (h * val)
				);
				break;
			case LEFT:
				container.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX) + (w - (int) (w * val)),
						((centerY - (h / 2)) + offsetY),
						(u + w) - ((int) (w * val)),
						v,
						(int) (w * val),
						h
				);
				break;
			case RIGHT:
				container.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX),
						((centerY - (h / 2)) + offsetY),
						u,
						v,
						(int) (w * val),
						h
				);
				break;
		}
	}
}

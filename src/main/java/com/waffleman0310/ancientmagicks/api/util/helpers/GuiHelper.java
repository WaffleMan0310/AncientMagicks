package com.waffleman0310.ancientmagicks.api.util.helpers;

import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.RotationModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class GuiHelper {

	public enum EnumDirection {
		UP, DOWN, LEFT, RIGHT
	}

	public static boolean isMouseInBounds(GuiScreen gui, int mouseX, int mouseY, int x, int y, int w, int h) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		int cornerX = centerX - (w / 2) + x;
		int cornerY = centerY - (h / 2) + y;

		return (mouseX >= cornerX) && (mouseY >= cornerY) && (mouseX <= (cornerX + w)) && (mouseY <= (cornerY + h));
	}

	public static void drawText(
			GuiScreen gui,
			String text,
			int x, int y,
			int color,
			@Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod
	) {

		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		/*
		This freezes the game?

		int w = 0;
		int h = gui.mc.fontRendererObj.FONT_HEIGHT;

		for (char c : text.toCharArray()) {
			w += gui.mc.fontRendererObj.getCharWidth(c);
		}
		*/

		drawWithModifiers(
				gui,
				x, y,
				scaleMod, rotMod,
				text(text, (centerX + x), (centerY + y), color)
		);
	}

	public static void drawTexture(
			GuiScreen gui,
			int u, int v,
			int w, int h,
			int x, int y,
			@Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod
	) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		drawWithModifiers(
				gui,
				x, y,
				scaleMod, rotMod,
				normal(((centerX - (w / 2)) + x), ((centerY - (h / 2)) + y), u, v, w, h)
		);
	}

	public static void drawTexture(
		GuiScreen gui,
		float u, float v,
		int uWidth, int vHeight,
		int w, int h,
		float tileWidth, float tileHeight,
		int x, int y,
		@Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod

	) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		drawWithModifiers(
				gui,
				x, y,
				scaleMod, rotMod,
				tiledScaled(((centerX - (w / 2)) + x), ((centerY - (h / 2)) + y), u, v, uWidth, vHeight, w, h, tileWidth, tileHeight)
		);
	}

	public static void drawTextureScaled(
			GuiScreen gui,
			int u, int v,
			int w, int h,
			int x, int y,
			@Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod,
			EnumDirection direction, float val
	) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		drawWithModifiers(
				gui,
				x, y,
				scaleMod, rotMod,
				scaled(((centerX - (w / 2)) + x), ((centerY - (h / 2)) + y), u, v, w, h, direction, val)
		);
	}

	private static void drawWithModifiers(
			GuiScreen gui,
			int x, int y,
			@Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod,
			Consumer<GuiScreen> consumer
			) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		GlStateManager.pushMatrix();

		GlStateManager.translate((centerX) + x, (centerY) + y, 0);

		if (scaleMod != null) {
			GlStateManager.scale(scaleMod.getX(), scaleMod.getY(), scaleMod.getZ());
		}

		if (rotMod != null) {
			GlStateManager.translate(rotMod.getX(), rotMod.getY(), rotMod.getZ());
			GlStateManager.rotate(rotMod.getAngle(), rotMod.getAxisX(), rotMod.getAxisY(), rotMod.getAxisZ());
			GlStateManager.translate(-rotMod.getX(), -rotMod.getY(), -rotMod.getZ());
		}

		GlStateManager.translate(-((centerX) + x), -((centerY) + y), 0);

		consumer.accept(gui);

		GlStateManager.popMatrix();
	}

	private static Consumer<GuiScreen> text(String text, int x, int y, int color) {
		return (guiScreen -> guiScreen.mc.fontRendererObj.drawStringWithShadow(text, x,  y, color));
	}

	private static Consumer<GuiScreen> normal(int x, int y, int u, int v, int w, int h) {
		return (guiScreen -> guiScreen.drawTexturedModalRect(x, y, u, v, w, h));
	}

	private static Consumer<GuiScreen> tiledScaled(int x, int y, float u, float v, int uWidth, int vHeight, int w, int h, float tileWidth, float tileHeight) {
		return (guiScreen -> Gui.drawScaledCustomSizeModalRect(x, y, u, v, uWidth, vHeight, w, h, tileWidth, tileHeight));
	}

	private static Consumer<GuiScreen> scaled(int x, int y, int u, int v, int w, int h, EnumDirection direction, float val) {
		return (guiScreen -> {
			float correctedVal = val > 1.0f ? 1.0f : val;

			switch (direction) {
				case UP:
					guiScreen.drawTexturedModalRect(x, y + (h - (int) (h * correctedVal)), u, v, w, (int) (h * correctedVal));
					break;
				case DOWN:
					guiScreen.drawTexturedModalRect(x, y, u, v, w, (int) (h * correctedVal));
					break;
				case LEFT:
					guiScreen.drawTexturedModalRect(x + (w - (int) (w * correctedVal)), y, (u + w) - ((int) (w * correctedVal)), v, (int) (w * correctedVal), h);
					break;
				case RIGHT:
					guiScreen.drawTexturedModalRect(x, y, u, v, (int) (w * correctedVal), h);
					break;
			}
		});
	}

	public static void drawTextureCentered(GuiScreen gui, int u, int v, int w, int h, int x, int y) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		gui.drawTexturedModalRect(
				(centerX - (w / 2)) + x,
				(centerY - (h / 2)) + y,
				u,
				v,
				w,
				h
		);
	}

	public static void drawTextureCenteredAndRotated(GuiScreen gui, int u, int v, int w, int h, int x, int y, float angle) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		GlStateManager.pushMatrix();

		GlStateManager.translate((centerX) + x, (centerY) + y, 0);
		GlStateManager.rotate(angle, 0, 0, 1);

		drawTextureCentered(gui, u, v, w, h, x, y);

		GlStateManager.translate(0, 0, 0);

		GlStateManager.popMatrix();
	}

	public static void drawCenteredTextureWithOffsetRotationAndScale(GuiScreen gui, int u, int v, int w, int h, int x, int y, float angle, float scale) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		GlStateManager.pushMatrix();

		GlStateManager.translate((centerX) + x, (centerY) + y, 0);
		GlStateManager.scale(scale, scale ,scale);
		GlStateManager.rotate(angle, 0, 0, 1);

		drawTextureCentered(gui, u, v, w, h, x, y);

		GlStateManager.translate(0, 0, 0);

		GlStateManager.popMatrix();
	}

	public static void drawCenteredScaledTextureWithOffset(GuiScreen gui, int u, int v, int w, int h, int offsetX, int offsetY, EnumDirection dir, float val) {
		int centerX = gui.width / 2;
		int centerY = gui.height / 2;

		switch (dir) {
			case UP:
				gui.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX),
						((centerY - (h / 2)) + offsetY) + (h - (int) (h * val)),
						u,
						v,
						w,
						(int) (h * val)
				);
				break;
			case DOWN:
				gui.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX),
						((centerY - (h / 2)) + offsetY),
						u,
						v,
						w,
						(int) (h * val)
				);
				break;
			case LEFT:
				gui.drawTexturedModalRect(
						((centerX - (w / 2)) + offsetX) + (w - (int) (w * val)),
						((centerY - (h / 2)) + offsetY),
						(u + w) - ((int) (w * val)),
						v,
						(int) (w * val),
						h
				);
				break;
			case RIGHT:
				gui.drawTexturedModalRect(
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

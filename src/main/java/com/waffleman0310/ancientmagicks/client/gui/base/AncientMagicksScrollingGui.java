package com.waffleman0310.ancientmagicks.client.gui.base;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.util.helpers.GuiHelper;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class AncientMagicksScrollingGui extends AncientMagicksGui{

	public static final int BORDER_THICKNESS = 5;

	public int xPosition;
	public int yPosition;

	private Texture background;

	private ScaleModifier backgroundScaleMod;

	private int viewportU, viewportV;
	private int viewportWidth, viewportHeight;

	private int nodefieldWidth, nodefieldHeight;
	private int scissorWidth, scissorHeight;

	private int lastMouseX, lastMouseY;

	public AncientMagicksScrollingGui(Texture background, int x, int y, int width, int height, float backgroundScale) {
		this.xPosition = x;
		this.yPosition = y;

		this.scissorWidth = width;
		this.scissorHeight = height;

		this.background = background;
		this.backgroundScaleMod = new ScaleModifier(backgroundScale);

		this.viewportU = this.background.getU();
		this.viewportV = this.background.getV();
		this.viewportWidth = ((this.background.getWidth() / 4) * 4) / 3;
		this.viewportHeight = ((this.background.getHeight() / 4) * 3) / 4;
	}

	@Override
	public void initGui() {
		super.initGui();

		for (GuiButton button : this.buttonList)  {
			this.nodefieldWidth = Math.max(this.nodefieldWidth, button.xPosition + button.width);
			this.nodefieldHeight = Math.max(this.nodefieldHeight, button.yPosition + button.height);
		}

		this.nodefieldWidth += (BORDER_THICKNESS * 2);
		this.nodefieldHeight += (BORDER_THICKNESS * 2);
		// set scale for the background to be the correct size for the buttons

	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {
		this.mc.getTextureManager().bindTexture(this.background.getTexture());


		// Twerk this out
		GuiHelper.drawTexture(
				this,
				viewportU, viewportV,
				viewportWidth, viewportHeight,
				viewportWidth, viewportHeight,
				viewportWidth, viewportHeight,
				this.xPosition, this.yPosition,
				null, null
		);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution scaledResolution = new ScaledResolution(this.mc);

		int scaleW = (int) (this.mc.displayWidth / scaledResolution.getScaledWidth_double());
		int scaleH = (int) (this.mc.displayHeight / scaledResolution.getScaledHeight_double());

		int scaledWidth = this.scissorWidth * scaleW;
		int scaledHeight = this.scissorHeight * scaleH;

		GL11.glEnable(GL11.GL_SCISSOR_TEST);

		// I fucking hate this stupid call.
		GL11.glScissor(
				(this.mc.displayWidth / 2) - (scaledWidth / 2),
				(this.mc.displayHeight / 2) - (scaledHeight / 2),
				scaledWidth, scaledHeight
		);

		super.drawScreen(mouseX, mouseY, partialTicks);

		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
		this.lastMouseX = mouseX;
		this.lastMouseY = mouseY;
	}

	@Override
	public void mouseClickMove(int mouseX, int mouseY, int button, long timeSinceLastClick) {

		moveViewport(-(mouseX - lastMouseX), -(mouseY - lastMouseY));

		this.lastMouseX = mouseX;
		this.lastMouseY = mouseY;
	}

	public void moveViewport(int deltaX, int deltaY) {
		boolean validX = true;
		boolean validY = true;

		if ((this.viewportU + deltaX + this.viewportWidth) > this.nodefieldWidth) {
			validX = false;
		}

		if ((this.viewportV + deltaY + this.viewportHeight) > this.nodefieldHeight) {
			validY = false;
		}

		if ((this.viewportU + deltaX) < 0) {
			validX = false;
		}

		if ((this.viewportV + deltaY) < 0) {
			validY = false;
		}

		if (validX) {
			this.viewportU += deltaX/*deltaX * (1.0f / this.viewportWidth)*/;
		}

		if (validY) {
			this.viewportV += deltaY/*deltaY * (1.0f / this.viewportHeight)*/;
		}

		for (GuiButton button : this.buttonList) {
			if (validX) {
				button.xPosition -= deltaX /* (1.0f / this.nodefieldWidth)*/;
			}
			if (validY) {
				button.yPosition -= deltaY/* * (1.0f / this.nodefieldHeight)*/;
			}
		}
	}
}

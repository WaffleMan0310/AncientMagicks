package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.api.util.helpers.GuiHelper;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.PositionModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.RotationModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGuiElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class GuiSchoolNode extends AncientMagicksGuiElement {

	public static final Random rand = new Random();

	public static final int SCHOOL_NODE_FRAME_U = 192;
	public static final int SCHOOL_NODE_FRAME_V = 192;
	public static final int SCHOOL_NODE_FRAME_WIDTH = 64;
	public static final int SCHOOL_NODE_FRAME_HEIGHT = 64;

	public static final int SCHOOL_NODE_LENS_U = 128;
	public static final int SCHOOL_NODE_LENS_V = 192;
	public static final int SCHOOL_NODE_LENS_WIDTH = 64;
	public static final int SCHOOL_NODE_LENS_HEIGHT = 64;

	public static final int LIGHTNING_U = 192;
	public static final int LIGHTNING_V = 128;
	public static final int LIGHTNING_WIDTH = 64;
	public static final int LIGHTNING_HEIGHT = 64;

	public static final int BACKGROUND_U = 128;
	public static final int BACKGROUND_V = 128;
	public static final int BACKGROUND_WIDTH = 64;
	public static final int BACKGROUND_HEIGHT = 64;

	public static final int SCHOOL_NODE_ICON_U = 250;
	public static final int SCHOOL_NODE_ICON_V = 479;
	public static final int SCHOOL_NODE_ICON_WIDTH = 32;
	public static final int SCHOOL_NODE_ICON_HEIGHT = 32;

	public static final int GENERAL_NODE_INDEX = 0;
	public static final int ALTERATION_NODE_INDEX = 1;
	public static final int ARTIFICE_NODE_INDEX = 2;
	public static final int AUTOMATA_NODE_INDEX = 3;
	public static final int ASTROMANCY_NODE_INDEX = 0;
	public static final int DEMONOLOGY_NODE_INDEX = 4;
	public static final int METAMORPHOSIS_NODE_INDEX = 7;
	public static final int WITCHCRAFT_NODE_INDEX = 6;
	public static final int WIZARDRY_NODE_INDEX = 5;

	public static final float NODE_SCALE = 1.0f;
	public static final float NODE_ROTATION_SPEED = 0.05f;
	public static final float NODE_GROWTH_FLUXUATION = 0.0005f;
	public static final float NODE_GROWTH_FLUXUATION_SPEED = (float) (Math.PI * 2) / 1000.0f;

	public static final float LIGHTNING_INCREMENT = 0.0025f;

	public static final ResourceLocation SCHOOL_NODE_TEXTURE = AncientMagicksUtil.getModResource("textures/gui/guide_book/main.png");

	private School school;

	private ScaleModifier scaleMod;
	private RotationModifier rotMod;

	private RotationModifier lightningRotMod;

	private float lightning = 1.0f;
	private float currentFluctuation = 0.0f;

	private boolean isHovered = false;

	public GuiSchoolNode(School school, int x, int y) {
		super(x, y);
		this.school = school;

		this.scaleMod = new ScaleModifier(NODE_SCALE);
		this.rotMod = new RotationModifier(0, 0, 1);

		this.lightningRotMod = new RotationModifier(0, 0, 1);
		this.lightningRotMod.setX(LIGHTNING_WIDTH / 2);
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialTicks) {

		this.mc.getTextureManager().bindTexture(SCHOOL_NODE_TEXTURE);

		this.isHovered = GuiHelper.isMouseInBounds(
				this,
				mouseX,
				mouseY,
				this.xCoord,
				this.yCoord,
				SCHOOL_NODE_FRAME_WIDTH,
				SCHOOL_NODE_FRAME_HEIGHT
		);

		// draw lens

		GlStateManager.pushMatrix();

		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();

		if (this.isHovered) {
			rotate(true);
			fluctuate();
		} else {
			if (rotMod.getAngle() > 0.0f && rotMod.getAngle() <= 180.0f) {
				rotate(false);
			} else if (rotMod.getAngle() > 180.0f && rotMod.getAngle() <= 360.0f) {
				rotate(true);
			}

			if (this.scaleMod.getX() > (NODE_SCALE + NODE_GROWTH_FLUXUATION_SPEED)) {
				fluctuate();
			}
		}

		GuiHelper.drawTexture(
				this,
				SCHOOL_NODE_LENS_U, SCHOOL_NODE_LENS_V,
				SCHOOL_NODE_LENS_WIDTH, SCHOOL_NODE_LENS_HEIGHT,
				this.xCoord, this.yCoord,
				scaleMod, null
		);

		GuiHelper.drawTexture(
				this,
				SCHOOL_NODE_FRAME_U, SCHOOL_NODE_FRAME_V,
				SCHOOL_NODE_FRAME_WIDTH, SCHOOL_NODE_FRAME_HEIGHT,
				this.xCoord, this.yCoord,
				scaleMod, rotMod
		);

		GlStateManager.popMatrix();
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

		this.mc.getTextureManager().bindTexture(SCHOOL_NODE_TEXTURE);

		GlStateManager.pushMatrix();

		GlStateManager.enableBlend();

		if (this.isHovered) {
			if (this.lightning >= 1.0f) {
				this.lightning = 0.0f;

				this.lightningRotMod.setAngle(MathHelper.getInt(rand, 0, 359));
			}

			lightning();
		} else {
			this.lightning = 0.0f;
			this.lightningRotMod.setAngle(0);
		}

		GuiHelper.drawTexture(
				this,
				BACKGROUND_U, BACKGROUND_V,
				BACKGROUND_WIDTH, BACKGROUND_HEIGHT,
				this.xCoord, this.yCoord,
				scaleMod, null
		);

		GlStateManager.popMatrix();
	}

	public boolean isHovered() {
		return this.isHovered;
	}

	public void fluctuate() {
		if (this.currentFluctuation >= (Math.PI * 2)) {
			this.currentFluctuation = 0.0f;
		} else {
			this.scaleMod.modify(NODE_GROWTH_FLUXUATION * MathHelper.sin(this.currentFluctuation));
			this.currentFluctuation += NODE_GROWTH_FLUXUATION_SPEED;
		}
	}

	public void rotate(boolean clockwise) {
		if (clockwise) {
			this.rotMod.incrementAngle(NODE_ROTATION_SPEED);
		} else {
			this.rotMod.incrementAngle(-NODE_ROTATION_SPEED);
		}

	}

	public void lightning() {
		if (this.isHovered) {

			GuiHelper.drawTextureScaled(
					this,
					LIGHTNING_U, LIGHTNING_V,
					LIGHTNING_WIDTH, LIGHTNING_HEIGHT,
					this.xCoord - (LIGHTNING_WIDTH / 2), this.yCoord,
					null, lightningRotMod,
					EnumDirection.LEFT, this.lightning
			);

			this.lightning += LIGHTNING_INCREMENT;
		}
	}
}

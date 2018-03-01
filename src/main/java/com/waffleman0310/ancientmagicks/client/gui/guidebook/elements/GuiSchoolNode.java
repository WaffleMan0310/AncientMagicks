package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.school.player.CapabilitySchool;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.api.util.helpers.GuiHelper;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.RotationModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGui;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class GuiSchoolNode extends AncientMagicksGuiButton {

	public static final Random rand = new Random();

	public static final float TWO_PI = (float) Math.PI * 2;

	public static final ResourceLocation SCHOOL_NODE_TEXTURE = AncientMagicksUtil.getModResource("textures/gui/guide_book/main.png");

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

	public static final int EXPERIENCE_FRAME_U = 0;
	public static final int EXPERIENCE_FRAME_V = 176;
	public static final int EXPERIENCE_FRAME_WIDTH = 64;
	public static final int EXPERIENCE_FRAME_HEIGHT = 16;

	public static final int EXPERIENCE_U = 64;
	public static final int EXPERIENCE_V = 176;

	public static final float NODE_SCALE = 1.0f;
	public static final float NODE_ROTATION_SPEED = 0.05f;
	public static final float NODE_GROWTH_FLUXUATION = 0.0005f;
	public static final float NODE_GROWTH_FLUXUATION_SPEED = TWO_PI / 1000.0f;

	public static final int MAX_LIGHTNING_INTERVAL = 50;
	public static final int MIN_LIGHTNING_INTERVAL = 20;

	public EntityPlayer player;
	public School school;
	private Texture schoolIcon;

	private ScaleModifier scaleMod;
	private ScaleModifier scaleModNoFlucuation;
	private RotationModifier rotMod;
	private RotationModifier lightningRotMod;

	private float fluctuation = 0.0f;
	private float lightning_interval = 0.0f;

	public GuiSchoolNode(int id, AncientMagicksGui parent, EntityPlayer player, School school, int x, int y) {
		super(parent, id, x, y, SCHOOL_NODE_FRAME_WIDTH, SCHOOL_NODE_FRAME_HEIGHT, school.getName());

		this.player = player;

		this.school = school;
		this.schoolIcon = this.school.getIconTexture();

		this.scaleMod = new ScaleModifier(NODE_SCALE);
		this.scaleModNoFlucuation = new ScaleModifier(NODE_SCALE);

		this.rotMod = new RotationModifier(0, 0, 1);

		this.lightningRotMod = new RotationModifier(0, 0, 1);
		this.lightningRotMod.setX(LIGHTNING_WIDTH / 2);
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialTicks) {
		this.parent.mc.getTextureManager().bindTexture(SCHOOL_NODE_TEXTURE);

		if (this.hovered) {
			rotate(true);
			fluctuate();
		} else {
			if (this.scaleMod.getX() > (NODE_SCALE + NODE_GROWTH_FLUXUATION_SPEED)) {
				fluctuate();
			}
		}

		drawFrame();
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {
		this.parent.mc.getTextureManager().bindTexture(SCHOOL_NODE_TEXTURE);

		if (this.hovered) {
			lightning();
		}

		drawBackground();
		drawExperienceBar();

		this.parent.mc.getTextureManager().bindTexture(this.schoolIcon.getTexture());

		drawIcon();
		drawLevel();
	}
	private void drawLevel() {
		int level = this.player.getCapability(CapabilitySchool.PLAYER_SCHOOL, null).getLevel(this.school);

		GuiHelper.drawText(
			this.parent,
			String.valueOf(level),
			this.xPosition - 2, this.yPosition - 17,
				-1,
			this.scaleModNoFlucuation.add(-0.2f), null
		);
	}

	private void drawExperienceBar() {
		GuiHelper.drawTexture(
				this.parent,
				EXPERIENCE_U,
				EXPERIENCE_V,
				EXPERIENCE_FRAME_WIDTH,
				EXPERIENCE_FRAME_HEIGHT ,
				this.xPosition, this.yPosition + 14,
				this.scaleModNoFlucuation.add(-0.65f), null
		);

		GuiHelper.drawTexture(
				this.parent,
				EXPERIENCE_FRAME_U,
				EXPERIENCE_FRAME_V,
				EXPERIENCE_FRAME_WIDTH,
				EXPERIENCE_FRAME_HEIGHT,
				this.xPosition, this.yPosition + 14,
				this.scaleModNoFlucuation.add(-0.65f), null
		);
	}

	private void drawIcon() {
		GuiHelper.drawTexture(
				this.parent,
				this.schoolIcon.getU(),
				this.schoolIcon.getV(),
				this.schoolIcon.getWidth(),
				this.schoolIcon.getHeight(),
				this.xPosition, this.yPosition,
				this.scaleModNoFlucuation.add(-0.45f), null
		);
	}

	private void drawFrame() {
		GuiHelper.drawTexture(
				this.parent,
				SCHOOL_NODE_LENS_U,
				SCHOOL_NODE_LENS_V,
				SCHOOL_NODE_LENS_WIDTH,
				SCHOOL_NODE_LENS_HEIGHT,
				this.xPosition, this.yPosition,
				scaleMod, null
		);

		GuiHelper.drawTexture(
				this.parent,
				SCHOOL_NODE_FRAME_U,
				SCHOOL_NODE_FRAME_V,
				SCHOOL_NODE_FRAME_WIDTH,
				SCHOOL_NODE_FRAME_HEIGHT,
				this.xPosition, this.yPosition,
				scaleMod, rotMod
		);
	}

	private void drawBackground() {
		GuiHelper.drawTexture(
				this.parent,
				BACKGROUND_U ,
				BACKGROUND_V,
				BACKGROUND_WIDTH,
				BACKGROUND_HEIGHT,
				this.xPosition, this.yPosition,
				scaleMod, null
		);
	}

	private void fluctuate() {
		if (this.fluctuation >= TWO_PI) {
			this.fluctuation = 0.0f;
		} else {
			this.scaleMod.modify(NODE_GROWTH_FLUXUATION * MathHelper.sin(this.fluctuation));

			this.width = SCHOOL_NODE_FRAME_WIDTH * (int) this.scaleMod.getX();
			this.height = SCHOOL_NODE_FRAME_HEIGHT * (int) this.scaleMod.getY();

			this.fluctuation += NODE_GROWTH_FLUXUATION_SPEED;
		}
	}

	private void rotate(boolean clockwise) {
		if (clockwise) {
			this.rotMod.incrementAngle(NODE_ROTATION_SPEED);
		} else {
			this.rotMod.incrementAngle(-NODE_ROTATION_SPEED);
		}
	}

	private void lightning() {
		if (this.lightning_interval == 0) {
			this.lightningRotMod.setAngle(MathHelper.getInt(rand, 0, 359));

			GuiHelper.drawTexture(
					this.parent,
					LIGHTNING_U,
					LIGHTNING_V,
					LIGHTNING_WIDTH,
					LIGHTNING_HEIGHT,
					this.xPosition - (LIGHTNING_WIDTH / 2), this.yPosition,
					null, lightningRotMod
			);

			this.lightning_interval = MathHelper.getInt(rand, MAX_LIGHTNING_INTERVAL, MIN_LIGHTNING_INTERVAL);
		}


		this.lightning_interval--;
	}
}

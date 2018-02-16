package com.waffleman0310.ancientmagicks.client.renderer.tileentity;

import com.waffleman0310.ancientmagicks.client.renderer.tileentity.base.AncientMagicksTESR;
import com.waffleman0310.ancientmagicks.common.blocks.BlockArcanistSmeltery;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.api.util.helpers.ModelHelper.PositionModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.ModelHelper.RotationModifier;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityArcanistSmelteryRender extends AncientMagicksTESR<TileEntityArcanistSmeltery> {

	private static final PositionModifier REAGENT_POS_MOD = new PositionModifier(2.0f, 3.5f, 0.5f);
	private static final RotationModifier REAGENT_ROT_MOD = new RotationModifier(0, 1, 0, -1.5f, 0, 0);

	private static final float ROTATION_INCREMENT = 0.05f;

	private static final float REAGENT_HEIGHT_FLUXUATION = 0.5f;
	private static final float REAGENT_HEIGHT_FREQ = (float) (Math.PI * 2) / 1500.0f;

	private float currentInterval = 0.0f;

	public TileEntityArcanistSmelteryRender() {

	}

	@Override
	public void renderTileEntityAt(TileEntityArcanistSmeltery te, double x, double y, double z, float partialTicks, int destroyStage, float partial) {

		boolean isFormed = te.getField(7) > 0;
		boolean isInfusing = te.getField(2) > 0;
		boolean isSmelting = te.getField(1) > 0;
		boolean isBurning = te.getField(0) > 0;

		renderModelFromState(
				te,
				te.getBlockType().getDefaultState().withProperty(BlockArcanistSmeltery.FORMED, true),
				null,
				null,
				null
		);

		renderModelFromState(
				te,
				Blocks.REAGENT_INFUSER.getDefaultState(),
				REAGENT_POS_MOD.add(0, REAGENT_HEIGHT_FLUXUATION * MathHelper.sin(this.currentInterval), 0),
				null,
				REAGENT_ROT_MOD
		);

		renderModelFromState(
				te,
				Blocks.REAGENT_INFUSER.getDefaultState(),
				REAGENT_POS_MOD.add(0, REAGENT_HEIGHT_FLUXUATION * MathHelper.sin(this.currentInterval + (2 * ((float) Math.PI / 3))), 0),
				null,
				REAGENT_ROT_MOD.add(120, 0, 0, 0, 0, 0, 0)
		);

		renderModelFromState(
				te,
				Blocks.REAGENT_INFUSER.getDefaultState(),
				REAGENT_POS_MOD.add(0, REAGENT_HEIGHT_FLUXUATION * MathHelper.sin(this.currentInterval + (4 * ((float) Math.PI / 3))), 0),
				null,
				REAGENT_ROT_MOD.add(240, 0, 0, 0, 0, 0, 0)
		);

		currentInterval += REAGENT_HEIGHT_FREQ;

		if (currentInterval >= (Math.PI * 2)) {
			currentInterval = 0;
		}

		REAGENT_ROT_MOD.incrementAngle(ROTATION_INCREMENT);
	}
}

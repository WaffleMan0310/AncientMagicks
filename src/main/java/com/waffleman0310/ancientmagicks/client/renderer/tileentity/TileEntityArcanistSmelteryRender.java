package com.waffleman0310.ancientmagicks.client.renderer.tileentity;

import com.waffleman0310.ancientmagicks.client.renderer.tileentity.base.AncientMagicksTESR;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class TileEntityArcanistSmelteryRender extends AncientMagicksTESR<TileEntityArcanistSmeltery> {

	public static final ResourceLocation SMELTERY_BASE_MODEL = AncientMagicksUtil.getModResource("models/arcanist_smeltery.obj");
	public static final ResourceLocation SMELTERY_REAGENT_MODEL = AncientMagicksUtil.getModResource("models/arcanist_smeltery_reagent.obj");
	public static final ResourceLocation SMELTERY_FURNACE_DOOR_MODEL = AncientMagicksUtil.getModResource("models/arcanist_smeltery_fuel_door.obj");
	public static final ResourceLocation SMELTERY_INPUT_DOOR_MODEL = AncientMagicksUtil.getModResource("models/arcanist_smeltery_input_door.obj");

	public TileEntityArcanistSmelteryRender() {
		super(SMELTERY_BASE_MODEL, SMELTERY_REAGENT_MODEL, SMELTERY_FURNACE_DOOR_MODEL, SMELTERY_INPUT_DOOR_MODEL);
	}

	@Override
	public void renderTileEntityFast(TileEntityArcanistSmeltery te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);

		renderModel(te, SMELTERY_BASE_MODEL, 0, 0, 0, buffer);

		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);
		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);
		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);

		renderModel(te, SMELTERY_FURNACE_DOOR_MODEL, 0, 0, 0, buffer);
		renderModel(te, SMELTERY_INPUT_DOOR_MODEL, 0, 0, 0, buffer);

		GlStateManager.popMatrix();

	}
}

package com.waffleman0310.ancientmagicks.client.renderer.tileentity;

import com.waffleman0310.ancientmagicks.client.renderer.tileentity.base.AncientMagicksTESR;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityArcanistSmelteryRender extends AncientMagicksTESR<TileEntityArcanistSmeltery> {

	public TileEntityArcanistSmelteryRender() {

	}

	@Override
	public void renderTileEntityAt(TileEntityArcanistSmeltery te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

		//this.bindTexture(AncientMagicksUtil.getModResource("textures/blocks/multiblocks/arcanists_smeltery.png"));
		//this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		renderModelFromState(te, te.getBlockType().getDefaultState());
		/*
		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);
		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);
		renderModel(te, SMELTERY_REAGENT_MODEL, 0, 0, 0, buffer);
		*/
	}
}

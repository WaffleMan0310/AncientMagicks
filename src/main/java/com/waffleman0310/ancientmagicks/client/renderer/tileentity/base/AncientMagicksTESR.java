package com.waffleman0310.ancientmagicks.client.renderer.tileentity.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;

import java.util.HashMap;

public abstract class AncientMagicksTESR <T extends TileEntity> extends TileEntitySpecialRenderer<T>{

	private HashMap<ResourceLocation, IBakedModel> models;

	public AncientMagicksTESR(ResourceLocation... models) {
		this.models = new HashMap<>(models.length);

		for (ResourceLocation model : models) {
			this.models.put(model, null);
		}
	}

	public void renderModel(T te, ResourceLocation model, double xOffset, double yOffset, double zOffset, BufferBuilder buffer) {
		if (isModelPresent(model)) {
			GlStateManager.translate(xOffset, yOffset, zOffset);

			World world = te.getWorld();
			BlockPos pos = te.getPos();

			Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(
					world,
					getModelBaked(model),
					world.getBlockState(pos),
					pos,
					buffer,
					true

			);

			GlStateManager.translate(-xOffset, -yOffset, -zOffset);
		}
	}

	private boolean isModelPresent(ResourceLocation modelLoc) {
		return this.models.containsKey(modelLoc);
	}

	private IBakedModel getModelBaked(ResourceLocation modelLoc) {
		if (isModelPresent(modelLoc)) {
			if (this.models.get(modelLoc) == null) {
				IModel model;
				try {
					model = OBJLoader.INSTANCE.loadModel(modelLoc);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				IBakedModel bakedModel = model.bake(
						model.getDefaultState(),
						DefaultVertexFormats.BLOCK,
						(resourceLocation) -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(resourceLocation.toString())
				);

				this.models.put(modelLoc, bakedModel);
			}

			return this.models.get(modelLoc);
		} else {
			return null;
		}
	}
}

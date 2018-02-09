package com.waffleman0310.ancientmagicks.client.renderer.tileentity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public abstract class AncientMagicksTESR <T extends TileEntity> extends TileEntitySpecialRenderer<T> {

	private HashMap<ResourceLocation, IBakedModel> models = new HashMap<>();

	public AncientMagicksTESR() { }

	public abstract void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer);

	@Override
	public void func_192841_a(T te, double x, double y, double z, float partialTicks, int destroyStage, float param) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		GlStateManager.translate(x, y, z);

		this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage, partialTicks, Tessellator.getInstance().getBuffer());

		GlStateManager.translate(-x, -y, -z);
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	public void renderModelFromState(T tileEntity, IBlockState state) {
		World world = tileEntity.getWorld();
		BlockPos pos = tileEntity.getPos();

		IBakedModel bakedModel = getDispatcher().getModelForState(state);

		GlStateManager.translate(-pos.getX(), -pos.getY(), -pos.getZ());
		RenderHelper.disableStandardItemLighting();

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (Minecraft.isAmbientOcclusionEnabled()) {
			GL11.glShadeModel(GL11.GL_SMOOTH);
		} else {
			GL11.glShadeModel(GL11.GL_FLAT);
		}
		getBufferBuilder().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);


		getDispatcher().getBlockModelRenderer().renderModel(
				world,
				bakedModel, // getModelBaked(model)
				state,
				pos,
				getBufferBuilder(),
				true

		);

		getTessellator().draw();

		RenderHelper.enableStandardItemLighting();
		GlStateManager.translate(pos.getX(), pos.getY(), pos.getZ());
	}

	public void renderModelFromResourceLocation(T tileEntity, ResourceLocation location, ResourceLocation texture) {
		World world = tileEntity.getWorld();
		BlockPos pos = tileEntity.getPos();
		IBlockState state = world.getBlockState(pos);

		IModel model = null;
		IBakedModel bakedModel = null;

		if (this.models.containsKey(location)) {
			bakedModel = this.models.get(location);
		} else {
			try {
				model = OBJLoader.INSTANCE.loadModel(location);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (model != null) {
				bakedModel = model.bake(
						TRSRTransformation.identity(),
						DefaultVertexFormats.BLOCK,
						(resourceLocation) -> new TextureAtlasSprite(resourceLocation.toString()) {
								@Override
								public float getUnInterpolatedU(float u) {
									return u / 16;
								}

								@Override
								public float getUnInterpolatedV(float v) {
									return v / -16;
								}
						}
				);
			}
		}

		if (bakedModel != null) {
			GlStateManager.translate(-pos.getX(), -pos.getY(), -pos.getZ());
			RenderHelper.disableStandardItemLighting();

			this.bindTexture(texture);

			if (Minecraft.isAmbientOcclusionEnabled()) {
				GL11.glShadeModel(GL11.GL_SMOOTH);
			} else {
				GL11.glShadeModel(GL11.GL_FLAT);
			}
			getBufferBuilder().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			getDispatcher().getBlockModelRenderer().renderModel(
					world,
					bakedModel,
					state,
					pos,
					getBufferBuilder(),
					true
			);

			getTessellator().draw();

			RenderHelper.enableStandardItemLighting();
			GlStateManager.translate(pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public Tessellator getTessellator() {
		return Tessellator.getInstance();
	}

	public BlockRendererDispatcher getDispatcher() {
		return Minecraft.getMinecraft().getBlockRendererDispatcher();
	}

	public BufferBuilder getBufferBuilder() {
		return getTessellator().getBuffer();
	}
}

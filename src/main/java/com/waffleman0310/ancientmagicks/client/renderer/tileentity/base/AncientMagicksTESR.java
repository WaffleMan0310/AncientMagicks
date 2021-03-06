package com.waffleman0310.ancientmagicks.client.renderer.tileentity.base;

import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.PositionModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.RotationModifier;
import com.waffleman0310.ancientmagicks.api.util.helpers.RenderHelper.ScaleModifier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public abstract class AncientMagicksTESR<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

	public AncientMagicksTESR() {
	}

	public abstract void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage, float partial);

	@Override
	public void func_192841_a(T te, double x, double y, double z, float partialTicks, int destroyStage, float param) {
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);

		this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage, partialTicks);

		GlStateManager.popMatrix();
	}

	public void renderItem(T tileEntity, ItemStack stack, @Nullable PositionModifier posMod, @Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod) {
		World world = tileEntity.getWorld();
		BlockPos pos = tileEntity.getPos();

		GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		RenderHelper.enableStandardItemLighting();

		if (posMod != null) {
			GlStateManager.translate(posMod.getX(), posMod.getY(), posMod.getZ());
		}

		if (scaleMod != null) {
			GlStateManager.scale(scaleMod.getX(), scaleMod.getY(), scaleMod.getZ());
		}

		if (rotMod != null) {
			GlStateManager.translate(rotMod.getX(), rotMod.getY(), rotMod.getZ());
			GlStateManager.rotate(rotMod.getAngle(), rotMod.getAxisX(), rotMod.getAxisY(), rotMod.getAxisZ());
			GlStateManager.translate(-rotMod.getX(), -rotMod.getY(), -rotMod.getZ());
		}

		//GlStateManager.translate(-pos.getX(), -pos.getY(), -pos.getZ());

		//System.out.println(stack.toString());
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.GROUND);

		GlStateManager.popMatrix();
	}

	public void renderModelFromState(T tileEntity, IBlockState state, @Nullable PositionModifier posMod, @Nullable ScaleModifier scaleMod, @Nullable RotationModifier rotMod) {
		World world = tileEntity.getWorld();
		BlockPos pos = tileEntity.getPos();

		IBakedModel bakedModel = getDispatcher().getModelForState(state);

		GlStateManager.pushMatrix();

		GlStateManager.disableLighting();
		RenderHelper.disableStandardItemLighting();

		if (posMod != null) {
			GlStateManager.translate(posMod.getX(), posMod.getY(), posMod.getZ());
		}

		if (scaleMod != null) {
			GlStateManager.scale(scaleMod.getX(), scaleMod.getY(), scaleMod.getZ());
		}

		if (rotMod != null) {
			GlStateManager.translate(rotMod.getX(), rotMod.getY(), rotMod.getZ());
			GlStateManager.rotate(rotMod.getAngle(), rotMod.getAxisX(), rotMod.getAxisY(), rotMod.getAxisZ());
			GlStateManager.translate(-rotMod.getX(), -rotMod.getY(), -rotMod.getZ());
		}

		GlStateManager.translate(-pos.getX(), -pos.getY(), -pos.getZ());

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		} else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
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
		GlStateManager.enableLighting();

		GlStateManager.popMatrix();
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

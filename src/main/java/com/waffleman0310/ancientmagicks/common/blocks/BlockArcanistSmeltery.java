package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.AncientMagicks;
import com.waffleman0310.ancientmagicks.api.block.IMachine;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksMultiBlock;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.handler.GuiHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockArcanistSmeltery extends AncientMagicksMultiBlock implements IMachine {

	public static final PropertyBool FORMED = PropertyBool.create("formed");

	public BlockArcanistSmeltery(String name) {
		super(name, Material.ROCK);
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(FORMED)) {
			// create the bounding box for the formed multiblock
		} else {
			return super.getBoundingBox(state, source, pos);
		}
		return super.getBoundingBox(state, source, pos);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(AncientMagicks.instance, GuiHandler.ARCANISTS_SMELTERY_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
			if (state.getValue(FORMED)) {
				// do the code for the item to activate the multiblock as well as consume resources and such
			} else {
				// do anything if not formed and activated
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileEntityArcanistSmeltery) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityArcanistSmeltery) tileEntity);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityArcanistSmeltery();
	}
}

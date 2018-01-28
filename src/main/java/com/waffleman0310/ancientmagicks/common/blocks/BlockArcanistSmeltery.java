package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.AncientMagicks;
import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.IResearchable;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.handler.GuiHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockArcanistSmeltery extends AncientMagicksBlock implements IResearchable, ITileEntityProvider {

    public BlockArcanistSmeltery(String name) {
        super(name, Material.GRASS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(AncientMagicks.instance, GuiHandler.ARCANISTS_SMELTERY_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer player) {

    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity instanceof TileEntityArcanistSmeltery) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityArcanistSmeltery) tileEntity);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public NodeType getType() {
        return null;
    }

    @Override
    public IResearchable[] getConnectedNodes() {
        return new IResearchable[0];
    }

    @Override
    public IResearchable[] getPrerequisites() {
        return new IResearchable[0];
    }

    @Override
    public ISchool getSchool() {
        return null;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityArcanistSmeltery();
    }
}
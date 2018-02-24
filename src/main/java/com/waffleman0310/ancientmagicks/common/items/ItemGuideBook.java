package com.waffleman0310.ancientmagicks.common.items;

import com.waffleman0310.ancientmagicks.AncientMagicks;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItem;
import com.waffleman0310.ancientmagicks.handler.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGuideBook extends AncientMagicksItem {

	public ItemGuideBook(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		BlockPos pos = playerIn.getPosition();

		if (!worldIn.isRemote) {
			playerIn.openGui(AncientMagicks.instance, GuiHandler.GUIDE_BOOK_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}

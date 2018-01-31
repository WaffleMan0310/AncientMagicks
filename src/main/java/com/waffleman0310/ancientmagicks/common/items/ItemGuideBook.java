package com.waffleman0310.ancientmagicks.common.items;

import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGuideBook extends AncientMagicksItem {

	public ItemGuideBook(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}

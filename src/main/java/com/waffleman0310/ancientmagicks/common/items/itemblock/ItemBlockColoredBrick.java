package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredBrick extends AncientMagicksItemBlock {

	public ItemBlockColoredBrick(AncientMagicksBlock block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumDyeColor color = EnumDyeColor.byMetadata(stack.getMetadata());

		return String.format("%s.%s", Block.getBlockFromItem(stack.getItem()).getUnlocalizedName(), color.getUnlocalizedName());
	}
}

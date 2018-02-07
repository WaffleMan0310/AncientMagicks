package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil.EnumResourcePrefix;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil.EnumResourceSuffix;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockLog extends AncientMagicksItemBlock {

	public ItemBlockLog(AncientMagicksBlock block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		// Implement
		return super.getItemBurnTime(itemStack);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		if (stack.getMetadata() == EnumTreeType.YGGDRASIL.getMetadata() || stack.getMetadata() == EnumTreeType.ARCANOC.getMetadata()) {
			return EnumRarity.UNCOMMON;
		}

		return super.getRarity(stack);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
		//tooltip.add(AncientMagicksUtil.localize(EnumResourceSuffix.DESC, this.getUnlocalizedName(stack)));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTreeType type = EnumTreeType.byMetadata(stack.getMetadata());
		return String.format("%s.%s", Block.getBlockFromItem(stack.getItem()).getUnlocalizedName(), type.getUnlocalizedName());
	}
}

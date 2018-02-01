package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import com.waffleman0310.ancientmagicks.variant.EnumReagentType;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockSapling extends AncientMagicksItemBlock {

	public ItemBlockSapling(AncientMagicksBlock block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.getMetadata() == EnumTreeType.YGGDRASIL.getMetadata()) {
			return true;
		} else {
			return super.hasEffect(stack);
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (stack.getMetadata() == EnumTreeType.YGGDRASIL.getMetadata()) {
			return EnumRarity.EPIC;
		} else if (stack.getMetadata() == EnumTreeType.ARCANOC.getMetadata()) {
			return EnumRarity.RARE;
		} else if (stack.getMetadata() == EnumTreeType.TIME_TWISTED.getMetadata()) {
			return EnumRarity.UNCOMMON;
		} else {
			return super.getRarity(stack);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTreeType type = EnumTreeType.byMetadata(stack.getMetadata());
		return String.format("%s.%s", Block.getBlockFromItem(stack.getItem()).getUnlocalizedName(), type.getUnlocalizedName());
	}
}

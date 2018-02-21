package com.waffleman0310.ancientmagicks.common.items.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class AncientMagicksItemTool extends ItemTool {

	protected AncientMagicksItemTool(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
	}
}

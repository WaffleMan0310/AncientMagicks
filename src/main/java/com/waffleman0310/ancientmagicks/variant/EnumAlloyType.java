package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum EnumAlloyType implements IStringSerializable {

	STEEL(0, MapColor.SILVER),
	MAGE_STEEL(1, MapColor.LIGHT_BLUE);

	private int meta;
	private MapColor color;

	EnumAlloyType(int meta, MapColor color, Item... parts) {

	}

	@Override
	public String getName() {
		return null;
	}
}

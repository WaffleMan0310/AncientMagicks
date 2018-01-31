package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumOreType implements IStringSerializable {
	BAUXITE(0, "bauxite", MapColor.BROWN),
	GALENA(1, "galena", MapColor.BLUE),
	CHALOCITE(2, "chalocite", MapColor.BROWN),
	CINNABAR(3, "cinnabar", MapColor.RED),
	SPERRYLITE(4, "sperrylite", MapColor.GREEN),
	URANITE(5, "uranite", MapColor.GREEN),
	WOLFRAMITE(6, "wolframite", MapColor.BLACK);

	private static final EnumOreType[] META_LOOKUP = new EnumOreType[values().length];
	private int meta;
	private String name;
	private MapColor color;

	EnumOreType(int meta, String name, MapColor color) {
		this.meta = meta;
		this.name = name;
		this.color = color;
	}

	public static EnumOreType byMetadata(int meta) {
		return META_LOOKUP[meta];
	}

	public int getMetadata() {
		return meta;
	}

	public String getUnlocalizedName() {
		return name;
	}

	public MapColor getMapColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}

	static {
		for (EnumOreType ore : EnumOreType.values()) {
			META_LOOKUP[ore.getMetadata()] = ore;
		}
	}
}

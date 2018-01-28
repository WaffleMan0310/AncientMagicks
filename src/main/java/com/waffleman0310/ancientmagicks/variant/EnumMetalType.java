package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumMetalType implements IStringSerializable {

    LEAD(0, "lead", MapColor.PURPLE),
    SILVER(1, "silver", MapColor.SILVER),
    COPPER(2, "copper", MapColor.BROWN),
    PLATINIUM(3, "platinium", MapColor.BLUE),
    TITANIUM(4, "titanium", MapColor.LIGHT_BLUE),
    BRONZE(5, "bronze", MapColor.BROWN),
    BRASS(6, "brass", MapColor.YELLOW),
    URANIUM(7, "uranium", MapColor.GREEN),
    TUNGSTEN(8, "tungsten", MapColor.BLACK),
    ARCANITE(9, "arcanite", MapColor.SILVER),
    ARCANIUM(10, "arcanium", MapColor.BLUE),
    MAGE_STEEL(11, "mage_steel", MapColor.BLUE);

    private static final EnumMetalType[] META_LOOKUP = new EnumMetalType[values().length];
    private int meta;
    private String name;
    private MapColor color;

    EnumMetalType(int meta, String name, MapColor color) {
        this.meta = meta;
        this.name = name;
        this.color = color;
    }

    EnumMetalType(int meta, String name, MapColor color, String oreDictKey) {

    }

    public String getUnlocalizedName() {
        return name;
    }

    public int getMetadata() {
        return meta;
    }

    public static EnumMetalType byMetadata(int meta) {
        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return name;
    }

    static {
        for (EnumMetalType metal : EnumMetalType.values()) {
            META_LOOKUP[metal.getMetadata()] = metal;
        }
    }
}

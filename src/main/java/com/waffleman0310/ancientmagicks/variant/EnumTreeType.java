package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public  enum EnumTreeType implements IStringSerializable {
        TIME_TWISTED(0, MapColor.BROWN),
        ARCANOC(1, MapColor.BROWN),
        YGGDRASIL(2, MapColor.BROWN);

        private static final EnumTreeType[] META_LOOKUP = new EnumTreeType[values().length];
        private String name;
        private int meta;
        private String unlocalizedName;
        private MapColor mapColor;

        EnumTreeType(int meta, MapColor mapColor) {
            this.mapColor = mapColor;
            this.name = this.name().toLowerCase();
            this.meta = meta;
            this.unlocalizedName = name;

        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }

        public static EnumTreeType byMetadata(int meta) {
            if (meta >= 0 && meta < EnumTreeType.values().length) {
                return META_LOOKUP[meta];
            }
            return null;
        }

        public int getMetadata() {
            return meta;
        }

        @Override
        public String getName() {
            return name;
        }

        static {
            for (EnumTreeType type : values()) {
                META_LOOKUP[type.getMetadata()] = type;
            }
        }
    }

package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.util.IStringSerializable;

public enum EnumReagentType implements IStringSerializable {

    ARCANOC_ROOT(0, "arcanoc_root"),
    SALT(1, "salt"),
    NITER(2, "niter"),
    SULFUR(3, "sulfur"),
    CINDERBLOOM(4, "cinderbloom"),
    WHISPERWIND(5, "whisperwind"),
    WORMGRASS(6, "wormgrass"),
    MANDRAKE(7, "mandrake"),
    TOADSTOOL(8, "toadstool"),
    HOLLY(9, "holly"),
    NETTLE(10, "nettle"),
    CELESTIAL_NITER(11, "celestial_niter"),
    ALCHEMICAL_SALT(12, "alchemical_salt"),
    VOLATILE_SULFUR(13, "volatile_sulfur"),
    TIME_TWISTED_TWIG(14, "time_twisted_twig"),
    VIAL_OF_DRAGONS_BLOOD(15, "vial_of_dragons_blood"),
    BOTTLED_CREEPER_BLOOD(16, "bottled_creeper_blood");

    private static final EnumReagentType[] META_LOOKUP = new EnumReagentType[EnumReagentType.values().length];
    private String name;
    private int meta;

    EnumReagentType(int meta, String name) {
        this.name = name;
        this.meta = meta;
    }

    public int getMetadata() {
        return this.meta;
    }

    public String getUnlocalizedName() {
        return this.name;
    }

    public static EnumReagentType byMetadata(int meta) {
        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return this.name;
    }

    static {
        for (EnumReagentType reagent : EnumReagentType.values()) {
            META_LOOKUP[reagent.getMetadata()] = reagent;
        }
    }
}

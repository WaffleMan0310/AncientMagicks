package com.waffleman0310.ancientmagicks.util;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


public class AncientMagicksUtil {
    public static final Logger logger = FMLLog.getLogger();

    public static final String modId = "ancientmagicks";
    public static final String name = "Ancient Magicks";
    public static final String version = "0.0.1";

    public enum EnumResourcePrefix {
        RESOURCE, SCHOOL, RESEARCH, BLOCK, ITEM, TOOLTIP
    }

    public enum EnumResourceSuffix {
        NAME, DESC
    }

    public static ResourceLocation getModResource(String path) {
        return new ResourceLocation(modId, path);
    }

    public static String formatUnlocalizedName(EnumResourcePrefix prefix, String name) {
        if (prefix != EnumResourcePrefix.BLOCK) {
            return String.format("%s.%s:%s", prefix.name().toLowerCase(), modId, name);
        }
        return String.format("%s:%s", modId, name);
    }

    public static String localize(EnumResourceSuffix type, String unlocalizedString) {
        return I18n.format(String.format("%s.%s", unlocalizedString, type.name().toLowerCase()));
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    public static void logf(Level level, String string, Object... data) {
        log(level, String.format(string, data));
    }
}

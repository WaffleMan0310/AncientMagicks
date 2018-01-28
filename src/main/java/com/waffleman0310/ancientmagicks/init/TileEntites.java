package com.waffleman0310.ancientmagicks.init;


import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntites {

    public static void registerAllTileEntities() {
        registerTileEntity(TileEntityArcanistSmeltery.class);
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
        GameRegistry.registerTileEntity(tileEntityClass, tileEntityClass.getName());
    }
}

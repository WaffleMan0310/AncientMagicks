package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.client.gui.GuiArcanistSmeltery;
import com.waffleman0310.ancientmagicks.common.container.ContainerArcanistSmeltery;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler{

    public static final int ARCANISTS_SMELTERY_GUI_ID = 0;
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ARCANISTS_SMELTERY_GUI_ID) {
            return new ContainerArcanistSmeltery(player.inventory, (TileEntityArcanistSmeltery) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ARCANISTS_SMELTERY_GUI_ID) {
            return new GuiArcanistSmeltery(player.inventory, (TileEntityArcanistSmeltery) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}

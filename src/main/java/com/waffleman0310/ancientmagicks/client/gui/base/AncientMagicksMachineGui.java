package com.waffleman0310.ancientmagicks.client.gui.base;

import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public abstract class AncientMagicksMachineGui extends GuiContainer {

    public static final ResourceLocation SLOT = AncientMagicksUtil.getModResource("textures/gui/slot.png");

    public AncientMagicksMachineGui(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Override
    public void drawHoveringText(List<String> textLines, int x, int y) {
        // Implement custom tooltip frames
        super.drawHoveringText(textLines, x, y);
    }
}

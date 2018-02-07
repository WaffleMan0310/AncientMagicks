package com.waffleman0310.ancientmagicks.client.gui.base;

import com.waffleman0310.ancientmagicks.api.mana.CapabilityMana;
import com.waffleman0310.ancientmagicks.api.mana.IManaStorage;
import com.waffleman0310.ancientmagicks.common.tileentity.base.TileEntityManaMachine;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.util.helpers.GuiHelper;
import com.waffleman0310.ancientmagicks.util.helpers.GuiHelper.EnumDirection;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public abstract class AncientMagicksManaMachineGui extends AncientMagicksMachineGui {

	public static final ResourceLocation MANA_GUI = AncientMagicksUtil.getModResource("textures/gui/mana_bar.png");

	/*------------ Tuning Variables ------------*/

	private static final int MANA_FRAME_WIDTH = 24;
	private static final int MANA_FRAME_HEIGHT = 73;
	private static final int MANA_FRAME_U = 0;
	private static final int MANA_FRAME_V = 0;

	private static final int MANA_BAR_WIDTH = 20;
	private static final int MANA_BAR_HEIGHT = 70;
	private static final int MANA_BAR_U = 24;
	private static final int MANA_BAR_V = 0;
	private static final int MANA_BAR_BG_U = 44;
	private static final int MANA_BAR_BG_V = 0;

	/*------------------------------------------*/

	private TileEntityManaMachine manaMachine;

	public AncientMagicksManaMachineGui(TileEntityManaMachine manaMachine, Container inventorySlotsIn) {
		super(inventorySlotsIn);

		this.manaMachine = manaMachine;
	}

	public void drawManaBar(int x, int y) {
		IManaStorage mana = this.manaMachine.getCapability(CapabilityMana.MANA, null);

		this.mc.getTextureManager().bindTexture(MANA_GUI);

		// Mana Background
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				MANA_BAR_BG_U,
				MANA_BAR_BG_V,
				MANA_BAR_WIDTH,
				MANA_BAR_HEIGHT,
				x,
				y
		);

		// Mana
		GuiHelper.drawCenteredScaledTextureWithOffset(
				this,
				MANA_BAR_U,
				MANA_BAR_V,
				MANA_BAR_WIDTH,
				MANA_BAR_HEIGHT,
				x,
				y,
				EnumDirection.UP,
				mana.getManaStored() / (float) mana.getManaCapacity()
		);

		// Mana Bar Frame
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				MANA_FRAME_U,
				MANA_FRAME_V,
				MANA_FRAME_WIDTH,
				MANA_FRAME_HEIGHT,
				x,
				y
		);

		this.mc.getTextureManager().bindTexture(SLOT);

		// Mana Slot
		GuiHelper.drawCenteredTextureWithOffset(this, 0, 0, 20, 20, x, y + 60);
	}

	public void drawManaBarToolTip(int x, int y, int mouseX, int mouseY) {
		// Tooltip
		// Further implement this and abstract where nessessary.
		String holdShiftPrompt = "Hold <LSHIFT> for information";
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			if (GuiHelper.isMouseInBoundsCenter(this, mouseX, mouseY, x, y, MANA_BAR_WIDTH, MANA_BAR_HEIGHT)) {
				ArrayList<String> toolTip = new ArrayList<>();
				String manaName = AncientMagicksUtil.localize(AncientMagicksUtil.EnumResourceSuffix.NAME, this.manaMachine.getManaUnlocalizedName());

				// Add net loss
				String howFull = String.format(
						"%d/%d (%.2f)",
						this.manaMachine.getManaStored(),
						this.manaMachine.getManaCapacity(),
						this.manaMachine.getManaStored() / (float) this.manaMachine.getManaCapacity()
				);
				String purity = String.format(
						"%s: %.2f (%.2fx consumption)",
						AncientMagicksUtil.localize(AncientMagicksUtil.EnumResourceSuffix.NAME, this.manaMachine.getManaPurityUnlocalizedName()),
						this.manaMachine.getManaPurity(),
						this.manaMachine.getPurityModifier()
				);

				toolTip.add(manaName);
				toolTip.add(howFull);
				toolTip.add(purity);

				this.drawHoveringText(toolTip, mouseX, mouseY);

			}
		}
	}
}

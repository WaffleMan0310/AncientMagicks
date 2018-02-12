package com.waffleman0310.ancientmagicks.client.gui.machines;

import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksManaMachineGui;
import com.waffleman0310.ancientmagicks.common.container.ContainerArcanistSmeltery;
import com.waffleman0310.ancientmagicks.common.crafting.ArcanistSmelteryRecipes;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.util.helpers.GuiHelper;
import com.waffleman0310.ancientmagicks.util.helpers.GuiHelper.EnumDirection;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiArcanistSmeltery extends AncientMagicksManaMachineGui {

	public static final ResourceLocation SMELTERY_GUI = AncientMagicksUtil.getModResource("textures/gui/arcanist_smeltery_gui.png");
	public static final ResourceLocation INVENTORY_GUI = AncientMagicksUtil.getModResource("textures/gui/inventory.png");

	/*------------ Tuning Variables ------------*/

	public static final int SMELTERY_BACKGROUND_WIDTH = 256;
	public static final int SMELTERY_BACKGROUND_HEIGHT = 169;
	public static final int SMELTERY_BACKGROUND_U = 0;
	public static final int SMELTERY_BACKGROUND_V = 0;
	public static final int SMELTERY_BACKGROUND_X = 0;
	public static final int SMELTERY_BACKGROUND_Y = -35;


	public static final int SMELTERY_FURNACE_WIDTH = 64;
	public static final int SMELTERY_FURNACE_HEIGHT = 40;
	public static final int SMELTERY_FURNACE_U = 0;
	public static final int SMELTERY_FURNACE_V = 169;
	public static final int SMELTERY_FURNACE_X = 0;
	public static final int SMELTERY_FURNACE_Y = 12;

	public static final int SMELTERY_FLAMES_WIDTH = 64;
	public static final int SMELTERY_FLAMES_HEIGHT = 35;
	public static final int SMELTERY_FLAMES_U = 64;
	public static final int SMELTERY_FLAMES_V = 204;
	public static final int SMELTERY_FLAMES_BG_U = 64;
	public static final int SMELTERY_FLAMES_BG_V = 169;

	public static final int SMELTERY_RUNE_WIDTH = 56;
	public static final int SMELTERY_RUNE_HEIGHT = 56;
	public static final int SMELTERY_RUNE_U = 128;
	public static final int SMELTERY_RUNE_V = 169;
	public static final int SMELTERY_RUNE_X = 0;
	public static final int SMELTERY_RUNE_Y = -40;

	public static final int SMELTERY_ARROW_WIDTH = 24;
	public static final int SMELTERY_ARROW_HEIGHT = 12;
	public static final int SMELTERY_ARROW_U = 217;
	public static final int SMELTERY_ARROW_V = 234;
	public static final int SMELTERY_ARROW_BG_U = 192;
	public static final int SMELTERY_ARROW_BG_V = 234;
	public static final int SMELTERY_ARROW_X = 41;
	public static final int SMELTERY_ARROW_Y = -40;

	public static final int SMELTERY_R_ARROW_WIDTH = 25;
	public static final int SMELTERY_R_ARROW_HEIGHT = 56;
	public static final int SMELTERY_R_ARROW_U = 217;
	public static final int SMELTERY_R_ARROW_V = 169;
	public static final int SMELTERY_R_ARROW_BG_U = 192;
	public static final int SMELTERY_R_ARROW_BG_V = 169;
	public static final int SMELTERY_R_ARROW_X = -42;
	public static final int SMELTERY_R_ARROW_Y = -45;

	public static final int MANA_FRAME_X = -86;
	public static final int MANA_FRAME_Y = SMELTERY_BACKGROUND_Y - 8;

	public static final int INVENTORY_WIDTH = 170;
	public static final int INVENTORY_HEIGHT = 83;
	public static final int INVENTORY_U = 0;
	public static final int INVENTORY_V = 0;
	public static final int INVENTORY_X = 0;
	public static final int INVENTORY_Y = 78;

	/*------------------------------------------*/
	private TileEntityArcanistSmeltery arcanistSmeltery;
	private IInventory playerInv;

	private float runeCircleRotation = 0;

	public GuiArcanistSmeltery(IInventory playerInv, TileEntityArcanistSmeltery arcanistSmeltery) {
		super(arcanistSmeltery, new ContainerArcanistSmeltery(playerInv, arcanistSmeltery));
		this.arcanistSmeltery = arcanistSmeltery;
		this.playerInv = playerInv;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawManaBarToolTip(MANA_FRAME_X, MANA_FRAME_Y, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		this.mc.getTextureManager().bindTexture(SMELTERY_GUI);

		// Background
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				SMELTERY_BACKGROUND_U,
				SMELTERY_BACKGROUND_V,
				SMELTERY_BACKGROUND_WIDTH,
				SMELTERY_BACKGROUND_HEIGHT,
				SMELTERY_BACKGROUND_X,
				SMELTERY_BACKGROUND_Y
		);

		// Furnace Background
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				SMELTERY_FLAMES_BG_U,
				SMELTERY_FLAMES_BG_V,
				SMELTERY_FLAMES_WIDTH,
				SMELTERY_FLAMES_HEIGHT,
				SMELTERY_FURNACE_X,
				SMELTERY_FURNACE_Y
		);

		// Furnace Flames (Needs logic)
		GuiHelper.drawCenteredScaledTextureWithOffset(
				this,
				SMELTERY_FLAMES_U,
				SMELTERY_FLAMES_V,
				SMELTERY_FLAMES_WIDTH,
				SMELTERY_FLAMES_HEIGHT,
				SMELTERY_FURNACE_X,
				SMELTERY_FURNACE_Y,
				EnumDirection.UP,
				getFuelLeftScaled()
		);

		// Furnace Grate
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				SMELTERY_FURNACE_U,
				SMELTERY_FURNACE_V,
				SMELTERY_FURNACE_WIDTH,
				SMELTERY_FURNACE_HEIGHT,
				SMELTERY_FURNACE_X,
				SMELTERY_FURNACE_Y
		);


		// Rune Circle rotate if smeltery is crafting
		if (runeCircleRotation > 0.0f || getTotalInfusionTime() - getInfusionTime() > 0 || getTotalSmeltTime() - this.getSmeltTime() > 0) {
			GuiHelper.drawCenteredTextureWithOffsetAndRotation(
					this,
					SMELTERY_RUNE_U,
					SMELTERY_RUNE_V,
					SMELTERY_RUNE_WIDTH,
					SMELTERY_RUNE_HEIGHT,
					SMELTERY_RUNE_X,
					SMELTERY_RUNE_Y,
					runeCircleRotation
			);

			if (runeCircleRotation > 0.0 && // Rune cirlce has rotation.
					((this.getInfusionTime() < this.getTotalInfusionTime()) && (this.arcanistSmeltery.getManaStored() == 0)) || // Recipe still infusing but no mana.
					((this.getSmeltTime() < this.getTotalSmeltTime()) && (this.getFuelLeft() == 0)) || // Recipe still smelting but no fuel.
					((!this.isRecipePresent())) // Recipe is no longer present.
					) {
				if (runeCircleRotation <= 180) {
					runeCircleRotation += 0.125;
				} else if (runeCircleRotation > 180) {
					runeCircleRotation -= 0.125;
				}
			}

			if (this.getInfusionTime() <= this.getTotalInfusionTime() || this.getSmeltTime() <= this.getTotalSmeltTime()) {
				if (runeCircleRotation < 360.0) {
					runeCircleRotation += 0.025f;
				} else {
					runeCircleRotation = 0.0f;
				}
			}

		} else {
			GuiHelper.drawCenteredTextureWithOffset(
					this,
					SMELTERY_RUNE_U,
					SMELTERY_RUNE_V,
					SMELTERY_RUNE_WIDTH,
					SMELTERY_RUNE_HEIGHT,
					SMELTERY_RUNE_X,
					SMELTERY_RUNE_Y
			);
		}

		// Reagent Arrow Background
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				SMELTERY_R_ARROW_BG_U,
				SMELTERY_R_ARROW_BG_V,
				SMELTERY_R_ARROW_WIDTH,
				SMELTERY_R_ARROW_HEIGHT,
				SMELTERY_R_ARROW_X,
				SMELTERY_R_ARROW_Y - 1
		);

		// Reagent Arrow Foreground (Needs Logic)
		GuiHelper.drawCenteredScaledTextureWithOffset(
				this,
				SMELTERY_R_ARROW_U,
				SMELTERY_R_ARROW_V,
				SMELTERY_R_ARROW_WIDTH,
				SMELTERY_R_ARROW_HEIGHT,
				SMELTERY_R_ARROW_X,
				SMELTERY_R_ARROW_Y,
				EnumDirection.RIGHT,
				getInfusionProgressScaled()
		);

		// Arrow Background
		GuiHelper.drawCenteredTextureWithOffset(
				this,
				SMELTERY_ARROW_BG_U,
				SMELTERY_ARROW_BG_V,
				SMELTERY_ARROW_WIDTH,
				SMELTERY_ARROW_HEIGHT,
				SMELTERY_ARROW_X,
				SMELTERY_ARROW_Y - 1
		);

		// Arrow Foreground (Needs Logic)
		GuiHelper.drawCenteredScaledTextureWithOffset(
				this,
				SMELTERY_ARROW_U,
				SMELTERY_ARROW_V,
				SMELTERY_ARROW_WIDTH,
				SMELTERY_ARROW_HEIGHT,
				SMELTERY_ARROW_X,
				SMELTERY_ARROW_Y,
				EnumDirection.RIGHT,
				getCookProgressScaled()
		);

		this.mc.getTextureManager().bindTexture(INVENTORY_GUI);

		GuiHelper.drawCenteredTextureWithOffset(
				this,
				INVENTORY_U,
				INVENTORY_V,
				INVENTORY_WIDTH,
				INVENTORY_HEIGHT,
				INVENTORY_X,
				INVENTORY_Y
		);

		this.drawManaBar(MANA_FRAME_X, MANA_FRAME_Y);

		this.mc.getTextureManager().bindTexture(SLOT);

		// Draw 3 Reagent slots
		for (int i = 0; i < 3; i++) {
			GuiHelper.drawCenteredTextureWithOffset(this, 0, 0, 20, 20, SMELTERY_R_ARROW_X - 20, (SMELTERY_R_ARROW_Y - 19) + (22 * i));
		}

		// Fuel Slot
		GuiHelper.drawCenteredTextureWithOffset(this, 0, 0, 20, 20, SMELTERY_FURNACE_X, SMELTERY_FURNACE_Y);
		// Input Slot
		GuiHelper.drawCenteredTextureWithOffset(this, 0, 0, 20, 20, SMELTERY_RUNE_X, SMELTERY_RUNE_Y);
		// Output Slot
		GuiHelper.drawCenteredTextureWithOffset(this, 0, 0, 20, 20, SMELTERY_RUNE_X + 65, SMELTERY_RUNE_Y);
	}

	public int getFuelBurnTime() {
		return this.arcanistSmeltery.getField(3);
	}

	public int getFuelLeft() {
		return this.arcanistSmeltery.getField(0);
	}

	public int getInfusionTime() {
		return this.arcanistSmeltery.getField(2);
	}

	public int getTotalInfusionTime() {
		return this.arcanistSmeltery.getField(5);
	}

	public int getSmeltTime() {
		return this.arcanistSmeltery.getField(1);
	}

	public int getTotalSmeltTime() {
		return this.arcanistSmeltery.getField(4);
	}

	public boolean infusionFinished() {
		return this.arcanistSmeltery.getField(6) > 0;
	}

	public boolean isFormed() {
		return this.arcanistSmeltery.getField(7) > 0;
	}

	public boolean isRecipePresent() {
		NonNullList<ItemStack> reagents = NonNullList.withSize(3, ItemStack.EMPTY);
		ItemStack inputStack = this.arcanistSmeltery.getStackInSlot(2);

		for (int i = 0; i < 3; i++) {
			reagents.set(i, this.arcanistSmeltery.getStackInSlot(i + 4));
		}

		return ArcanistSmelteryRecipes.instance().containsAllReagents(inputStack, reagents);
	}

	private float getInfusionProgressScaled() {
		int infusionTime = this.getInfusionTime();
		int totalInfusionTime = this.getTotalInfusionTime();
		return infusionTime / (float) (totalInfusionTime > 0 ? totalInfusionTime : 1);
	}

	private float getCookProgressScaled() {
		int cookTime = this.getSmeltTime();
		int totalCookTime = this.getTotalSmeltTime();
		return cookTime / (float) (totalCookTime > 0 ? totalCookTime : 1);
	}

	private float getFuelLeftScaled() {
		int fuelLeft = this.getFuelLeft();
		int fuelBurnTime = this.getFuelBurnTime();
		return fuelLeft / (float) (fuelBurnTime > 0 ? fuelBurnTime : 1);
	}
}

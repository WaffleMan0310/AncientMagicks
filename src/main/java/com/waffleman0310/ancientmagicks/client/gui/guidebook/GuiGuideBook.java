package com.waffleman0310.ancientmagicks.client.gui.guidebook;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiResearchPane;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiSchoolPane;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiSchoolNode;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GuiGuideBook extends GuiSchoolPane {

	// add textures
	public static final ResourceLocation GUIDE_BOOK_TEXTURE = AncientMagicksUtil.getModResource("textures/gui/guide_book/main.png");

	public static final Texture MAIN_BACKGROUND = new Texture(AncientMagicksUtil.getModResource("textures/gui/guide_book/background.png"), 0, 0, 1024, 1024);

	public static final int GUIDE_BOOK_X = 0;
	public static final int GUIDE_BOOK_Y = 0;
	public static final int GUIDE_BOOK_WIDTH = 500;
	public static final int GUIDE_BOOK_HEIGHT = 275;

	public List<GuiResearchPane> schoolResearchPanes = new ArrayList<>();

	public GuiGuideBook(EntityPlayer player) {
		super(player, MAIN_BACKGROUND, GUIDE_BOOK_X, GUIDE_BOOK_Y, GUIDE_BOOK_WIDTH, GUIDE_BOOK_HEIGHT, 6.0f);

		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();

		this.schoolResearchPanes.clear();

		SCHOOLS.forEach(school -> this.schoolResearchPanes.add(new GuiResearchPane(
							school,
							this.player,
							MAIN_BACKGROUND,
							GUIDE_BOOK_X, GUIDE_BOOK_Y,
							GUIDE_BOOK_WIDTH, GUIDE_BOOK_HEIGHT,
							6.0f
		)));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof GuiSchoolNode) {
			GuiSchoolNode node = (GuiSchoolNode) button;
			this.schoolResearchPanes.forEach(pane -> {
				if (pane.school.equals(node.school)) {
					this.mc.displayGuiScreen(pane);
				}
			});
		}

		// revert to original gui on close
	}
}

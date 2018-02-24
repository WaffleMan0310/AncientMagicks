package com.waffleman0310.ancientmagicks.client.gui.guidebook;

import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGui;
import com.waffleman0310.ancientmagicks.client.gui.guidebook.elements.GuiSchoolNode;
import com.waffleman0310.ancientmagicks.common.registries.AncientMagicksRegistries;
import com.waffleman0310.ancientmagicks.init.Schools;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;


public class GuiGuideBook extends AncientMagicksGui {

	// add textures
	public static final ResourceLocation GUIDE_BOOK_TEXTURE = AncientMagicksUtil.getModResource("textures/gui/guide_book/main.png");

	private IPlayerResearch playerResearch;

	public final GuiSchoolNode GENERAL = new GuiSchoolNode(Schools.GENERAL, 100, 100);
	public final GuiSchoolNode ARTIFICE = new GuiSchoolNode(Schools.ARTIFICE, 0, 0);
	public final GuiSchoolNode ALTERATION = new GuiSchoolNode(Schools.ALTERATION, 0, 0);
	public final GuiSchoolNode AUTOMATA = new GuiSchoolNode(Schools.AUTOMATA, 0, 0);
	public final GuiSchoolNode WITCHCRAFT = new GuiSchoolNode(Schools.WITCHCRAFT, 0, 0);
	public final GuiSchoolNode DEMONOLOGY = new GuiSchoolNode(Schools.DEMONOLOGY, 0, 0);
	public final GuiSchoolNode METAMORPHOSIS = new GuiSchoolNode(Schools.METAMORPHOSIS, 0, 0);
	public final GuiSchoolNode WIZARDRY = new GuiSchoolNode(Schools.WIZARDRY, 0, 0);

	public GuiGuideBook(EntityPlayer player) {
		this.playerResearch = player.getCapability(CapabilityResearch.RESEARCH, null);

		addElement(GENERAL);
		//addElement(ARTIFICE);
		//addElement(ALTERATION);
		//addElement(AUTOMATA);
		//addElement(WITCHCRAFT);
		//addElement(DEMONOLOGY);
		//addElement(METAMORPHOSIS);
		//addElement(WIZARDRY);
	}


	@Override
	public void drawForground(int mouseX, int mouseY, float partialticks) {
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {

	}
}

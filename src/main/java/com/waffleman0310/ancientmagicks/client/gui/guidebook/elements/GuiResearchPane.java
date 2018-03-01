package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchNode;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchRegistry;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksScrollingGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

import java.io.IOException;

public class GuiResearchPane extends AncientMagicksScrollingGui {

	public enum EnumConnectType {
		STRAIGHT, RIGHT_ANGLE
	}

	public School school;
	public ResearchRegistry<IResearchEntry> researchRegistry;

	public EntityPlayer player;

	public GuiResearchPane(School school, EntityPlayer player, Texture background, int x, int y, int width, int height, float backgroundScale) {
		super(background, x, y, width, height, backgroundScale);

		this.school = school;
		this.researchRegistry = this.school.getResearchRegistry();

		this.player = player;

		// add a back button that opens the guide book gui again
	}

	@Override
	public void initGui() {
		super.initGui();

		this.researchRegistry.forEach(this::addResearchNode);
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {
		super.drawBackground(mouseX, mouseY, partialTicks);
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialticks) {
		super.drawForground(mouseX, mouseY, partialticks);
	}

	public void addResearchNode(ResearchNode<IResearchEntry> entry) {
		int id = this.researchRegistry.getID(entry.getResearch());
		int[] pos = getNextNodePos(entry);

		GuiResearchNode node = new GuiResearchNode(this, entry.getResearch(), id, pos[0], pos[1]);

		this.addButton(node);
	}

	public int[] getNextNodePos(ResearchNode<IResearchEntry> entry) {
		return new int[2];
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

	// add functions for connecting nodes
}

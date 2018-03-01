package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.client.gui.Texture;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksGui;
import com.waffleman0310.ancientmagicks.client.gui.base.AncientMagicksScrollingGui;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

public class GuiSchoolPane extends AncientMagicksScrollingGui {

	public static final ForgeRegistry<School> SCHOOLS = (ForgeRegistry<School>) GameRegistry.findRegistry(School.class);

	public EntityPlayer player;

	public GuiSchoolPane(EntityPlayer player, Texture background, int x, int y, int width, int height, float backgroundScale) {
		super(background, x, y, width, height, backgroundScale);

		this.player = player;
	}

	@Override
	public void initGui() {
		SCHOOLS.forEach(school -> this.addSchoolNode(this, school));

		super.initGui();
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTicks) {
		super.drawBackground(mouseX, mouseY, partialTicks);
	}

	@Override
	public void drawForground(int mouseX, int mouseY, float partialticks) {
		super.drawForground(mouseX, mouseY, partialticks);
	}

	public void addSchoolNode(AncientMagicksGui parent, School school) {
		int id = SCHOOLS.getID(school);
		int[] pos = getNextNodePos(id);

		GuiSchoolNode node = new GuiSchoolNode(id, parent, this.player, school, pos[0], pos[1]);

		this.addButton(node);
	}

	private int[] getNextNodePos(int id) {
		int[] coords = new int[2];

		coords[0] = id * 50;
		coords[1] = 0;

		return coords;
	}

	// add functions for connecting nodes
}

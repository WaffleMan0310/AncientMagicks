package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraft.client.gui.Gui;

public class GuiSchoolNode extends Gui {

	School school;

	public GuiSchoolNode(School school) {
		this.school = school;
	}
}

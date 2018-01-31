package com.waffleman0310.ancientmagicks.client.gui.guidebook.elements;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.schools.EnumSchool;
import net.minecraft.client.gui.Gui;

public class GuiSchoolNode extends Gui {

	ISchool school;

	public GuiSchoolNode(EnumSchool school) {
		this.school = school.getSchool();
	}
}

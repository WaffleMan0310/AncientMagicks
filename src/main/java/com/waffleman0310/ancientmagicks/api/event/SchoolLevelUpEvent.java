package com.waffleman0310.ancientmagicks.api.event;

import com.waffleman0310.ancientmagicks.api.school.School;

import net.minecraftforge.fml.common.eventhandler.Event;

public class SchoolLevelUpEvent extends Event {

	private School school;
	private int level;

	public SchoolLevelUpEvent(School school, int level) {
		this.school = school;
		this.level = level;
	}

	public int getOldLevel() {
		return this.level - 1;
	}

	public int getNewLevel() {
		return this.level;
	}

	public School getSchool() {
		return this.school;
	}
}

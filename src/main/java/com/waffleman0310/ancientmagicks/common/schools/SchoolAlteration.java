package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolAlteration extends School {

	public SchoolAlteration(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "alteration";
	}

	@Override
	public String getResourceName() {
		return "glyphs";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}

}

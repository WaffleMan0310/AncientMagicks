package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolWizardry extends School {

	public SchoolWizardry(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "wizardry";
	}

	@Override
	public String getResourceName() {
		return "dark_matter";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}

package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public class SchoolWizardry implements ISchool {

	@Override
	public String getName() {
		return "wizardry";
	}

	@Override
	public String getResourceName() {
		return "dark_matter";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}
}

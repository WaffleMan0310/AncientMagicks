package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public class SchoolMetamorphosis implements ISchool {

	@Override
	public String getName() {
		return "metamorphosis";
	}

	@Override
	public String getResourceName() {
		return "runic_power";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}
}

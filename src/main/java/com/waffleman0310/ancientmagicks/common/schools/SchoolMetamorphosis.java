package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolMetamorphosis extends School {

	public SchoolMetamorphosis(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "metamorphosis";
	}

	@Override
	public String getResourceName() {
		return "runic_power";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}

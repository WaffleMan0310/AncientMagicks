package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public class SchoolAutomata implements ISchool {

	@Override
	public String getName() {
		return "automata";
	}

	@Override
	public String getResourceName() {
		return "arcanetic_energy";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}
}

package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolAutomata extends School {

	public SchoolAutomata(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "automata";
	}

	@Override
	public String getResourceName() {
		return "arcanetic_energy";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}

package com.waffleman0310.ancientmagicks.api.school.player;

import com.waffleman0310.ancientmagicks.api.school.School;

import java.util.HashMap;

public interface IPlayerSchool {

	void addExperiance(School school, int amount);

	int getExperience(School school);

	int getExperianceToNextLevel(School school);

	int getLevel(School school);
}

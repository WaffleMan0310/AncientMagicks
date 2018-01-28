package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolWizardry implements ISchool {

    public static final SchoolWizardry INSTANCE = new SchoolWizardry();

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

    @Override
    public ResearchMap getResearchMap() {
        return null;
    }
}

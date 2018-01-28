package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolWitchcraft implements ISchool {

    public static final SchoolWitchcraft INSTANCE = new SchoolWitchcraft();

    @Override
    public String getName() {
        return "witchcraft";
    }

    @Override
    public String getResourceName() {
        return "brewing_base";
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
package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolAutomata implements ISchool {

    public static final SchoolAutomata INSTANCE = new SchoolAutomata();

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

    @Override
    public ResearchMap getResearchMap() {
        return null;
    }
}
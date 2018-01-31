package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public enum EnumSchool {
    GENERAL(new SchoolGeneral()),
    ALTERATION(new SchoolAlteration()),
    ARTIFICE(new SchoolArtifice()),
    ASTROMANCY(new SchoolAstromancy()),
    AUTOMATA(new SchoolAutomata()),
    DEMONOLOGY(new SchoolDemonology()),
    METAMORPHOSIS(new SchoolMetamorphosis()),
    WITCHCRAFT(new SchoolWitchcraft()),
    WIZARDRY(new SchoolWizardry());

    private ISchool school;

    EnumSchool(ISchool school) {
        this.school = school;
    }

    public ISchool getSchool() {
        return school;
    }
}

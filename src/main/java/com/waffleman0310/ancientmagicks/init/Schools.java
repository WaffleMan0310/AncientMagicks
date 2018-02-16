package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.common.registries.AncientMagicksRegistries;
import com.waffleman0310.ancientmagicks.common.schools.*;

public class Schools {

	public static final School GENERAL;
	public static final School ALTERATION;
	public static final School ARTIFICE;
	public static final School ASTROMANCY;
	public static final School DEMONOLOGY;
	public static final School METAMORPHOSIS;
	public static final School WITCHCRAFT;
	public static final School WIZARDRY;

	public static void registerAllSchools() {
		registerSchool(GENERAL);
		registerSchool(ALTERATION);
		registerSchool(ARTIFICE);
		registerSchool(ASTROMANCY);
		registerSchool(DEMONOLOGY);
		registerSchool(METAMORPHOSIS);
		registerSchool(WITCHCRAFT);
		registerSchool(WIZARDRY);
	}

	public static void registerSchool(School school) {
		AncientMagicksRegistries.SCHOOLS.register(school);
	}

	static {
		GENERAL = new SchoolGeneral("general");
		ALTERATION = new SchoolAlteration("alteration");
		ARTIFICE = new SchoolArtifice("artifice");
		ASTROMANCY = new SchoolAstromancy("astromancy");
		DEMONOLOGY = new SchoolDemonology("demonolgy");
		METAMORPHOSIS = new SchoolMetamorphosis("metamorphosis");
		WITCHCRAFT = new SchoolWitchcraft("witchcraft");
		WIZARDRY = new SchoolWizardry("wizardry");
	}
}

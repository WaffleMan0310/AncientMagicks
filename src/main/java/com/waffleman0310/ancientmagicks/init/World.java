package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.world.feature.gen.ore.*;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class World {

    public static final OreGenBauxite BAUXITE;
    public static final OreGenChalocite CHALOCITE;
    public static final OreGenCinnabar CINNABAR;
    public static final OreGenGalena GALENA;
    public static final OreGenSperrylite SPERRYLITE;
    public static final OreGenUranite URANITE;
    public static final OreGenWolframite WOLFRAMITE;

    public static void registerAllGenerators() {
        register(BAUXITE);
        register(CHALOCITE);
        register(CINNABAR);
        register(GALENA);
        register(SPERRYLITE);
        register(URANITE);
        register(WOLFRAMITE);
    }


    public static void register(IWorldGenerator generator) {
        GameRegistry.registerWorldGenerator(generator, 0);
    }

    static {
        BAUXITE = new OreGenBauxite();
        CHALOCITE = new OreGenChalocite();
        CINNABAR = new OreGenCinnabar();
        GALENA = new OreGenGalena();
        SPERRYLITE = new OreGenSperrylite();
        URANITE = new OreGenUranite();
        WOLFRAMITE = new OreGenWolframite();
    }
}

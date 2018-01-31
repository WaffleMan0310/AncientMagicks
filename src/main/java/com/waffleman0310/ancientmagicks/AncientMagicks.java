package com.waffleman0310.ancientmagicks;

import com.waffleman0310.ancientmagicks.proxy.CommonProxy;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
Separate ItemMetal into ItemIngot and ItemNugget, or make all
block and items have their own register functions, so situations like logs can have an enum as large
as needed

Shift the getName mechanism in block and item to using the registry name instead

More work on leaves

Make trunk a collections of ellipses that converge to the beginnging of the skeleton.... exponential function?

Make wood just inherit from a superclass with axis property and remove enum system?

Better line / everything drwaing algorithms

Textures, textures, textures...

Tune variables for rest of the trees

Research various different types of magic throughout history and use knowledge to add more items and develop schools more.

Start implementing the guide book.

Solve the NPE the ManaPacket is throwing.

Create an abstract set of packet classes as a foundation for c->s communication in the mod. Make it easy to send certain pieces of information.

Brainstorm on machines and items that would be useful, moreso than just the usual ore processing route with auto miners. Although those will be added, in my
own unique way.

Start making models in blender to use for machines.

Implement the building blocks for multiblocks.

Implement Rifts - Decide if the dimensional space is randomly generated with a few constants, or fully constant

Add comments throughout all existing code, not only to build a habit. But because it makes life easier for everyone including you

Follow up: Add custom tooltip frames as well as the ability to add custom colored text to the tooltip

Random Idea: Alchemy multiblock is a giant cauldron with a giant alchemist statue that actually puts the items in the cauldron.

Implement the RF tile entity class, as well as the combination RF and manaStorage consumer.

Start work on manaStorage transportation and working with rendering manaStorage as it transports through crystals.

Instead of current research map system, implement Map and create it from there?

 */


@Mod(
		modid = AncientMagicksUtil.modId,
		name = AncientMagicksUtil.name,
		version = AncientMagicksUtil.version)
public class AncientMagicks {

	@Mod.Instance(AncientMagicksUtil.modId)
	public static AncientMagicks instance;

	@SidedProxy(
			clientSide = "com.waffleman0310.ancientmagicks.proxy.ClientProxy",
			serverSide = "com.waffleman0310.ancientmagicks.proxy.SeverProxy"
	)

	public static CommonProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event) {
		proxy.preInitialization(event);
	}

	@EventHandler
	public void intitialization(FMLInitializationEvent event) {
		proxy.initialization(event);
	}

	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event) {
		proxy.postInitialization(event);
	}
}

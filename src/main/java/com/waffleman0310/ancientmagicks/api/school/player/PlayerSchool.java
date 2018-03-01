package com.waffleman0310.ancientmagicks.api.school.player;

import com.waffleman0310.ancientmagicks.api.event.SchoolLevelUpEvent;
import com.waffleman0310.ancientmagicks.api.school.School;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public class PlayerSchool implements IPlayerSchool{

	protected final HashMap<School, Integer> schoolLevelMap = new HashMap<>();
	protected final HashMap<School, Integer> schoolExperianceMap = new HashMap<>();

	public PlayerSchool() {
		IForgeRegistry<School> schoolRegistry = GameRegistry.findRegistry(School.class);

		schoolRegistry.forEach(school -> {
			schoolExperianceMap.put(school, 0);
			schoolLevelMap.put(school, 1);
		});
	}

	@Override
	public void addExperiance(School school, int amount) {
		float newAmount = this.schoolExperianceMap.get(school) + amount;

		if (newAmount >= getExperianceToNextLevel(school)) {
			// fire level up event
			this.schoolLevelMap.put(school, this.schoolLevelMap.get(school) + 1);
			this.schoolExperianceMap.put(school, amount - getExperianceToNextLevel(school));

			MinecraftForge.EVENT_BUS.post(new SchoolLevelUpEvent(school, this.schoolLevelMap.get(school)));
		} else {
			this.schoolExperianceMap.put(school, this.schoolExperianceMap.get(school) + amount);
		}
	}

	@Override
	public int getExperience(School school) {
		return this.schoolExperianceMap.get(school);
	}

	@Override
	public int getExperianceToNextLevel(School school) {
		return (int) Math.pow(2, this.schoolLevelMap.get(school));
	}

	@Override
	public int getLevel(School school) {
		return this.schoolLevelMap.get(school);
	}
}

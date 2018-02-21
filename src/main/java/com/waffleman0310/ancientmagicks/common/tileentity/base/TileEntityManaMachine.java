package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.mana.CapabilityMana;
import com.waffleman0310.ancientmagicks.api.mana.IMana;
import com.waffleman0310.ancientmagicks.api.mana.IManaStorage;
import com.waffleman0310.ancientmagicks.api.mana.ManaStorage;
import com.waffleman0310.ancientmagicks.api.tileentity.IManaMachine;
import com.waffleman0310.ancientmagicks.common.network.ManaPacket;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import javax.annotation.Nullable;

public abstract class TileEntityManaMachine extends TileEntityMachine implements IManaMachine {

	protected ManaStorage manaStorage;

	public TileEntityManaMachine(int inventorySlots, long capacity, long maxRecieve, IMana.EnumManaType type) {
		super(inventorySlots);
		manaStorage = new ManaStorage(capacity, 0, maxRecieve, type);
	}

	public void sendManaToClient() {
		PacketHandler.INSTANCE.sendToAllAround(
				new ManaPacket(this.getPos(), this.getManaStored()),
				new TargetPoint(
						Minecraft.getMinecraft().world.provider.getDimension(),
						this.getPos().getX(),
						this.getPos().getY(),
						this.getPos().getZ(),
						4
				)
		);
	}

	@Override
	public ManaStorage getManaStorage() {
		return this.manaStorage;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		manaStorage.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		manaStorage.writeToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		return (capability == CapabilityMana.MANA || super.hasCapability(capability, side));
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityMana.MANA) {
			return CapabilityMana.MANA.cast(this.manaStorage);
		}

		return super.getCapability(capability, side);
	}
}

package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.mana.*;
import com.waffleman0310.ancientmagicks.api.tileentity.IManaMachine;
import com.waffleman0310.ancientmagicks.common.network.ManaPacket;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public abstract class TileEntityManaMachine extends TileEntityMachine implements IManaMachine {

    protected ManaStorage manaStorage;

    public TileEntityManaMachine(int inventorySlots, long capacity, long maxRecieve, IMana.EnumManaType type) {
        super(inventorySlots);
        manaStorage = new ManaStorage(capacity, 0, maxRecieve, type);
    }

    @Override
    public ManaStorage getManaStorage() {
        return this.manaStorage;
    }

    @Override
    public void update() {
        sendManaDataToClient(this.world, this.pos, this.manaStorage);
    }

    protected static void sendManaDataToClient(World worldIn, BlockPos pos, ManaStorage manaStorage){
        if (!worldIn.isRemote) {
            PacketHandler.INSTANCE.sendToAllAround(
                    new ManaPacket(manaStorage.getManaStored(), pos),
                    new NetworkRegistry.TargetPoint(
                            worldIn.provider.getDimension(),
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            8
                    )
            );
        }
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
            return CapabilityMana.MANA.cast(new IManaStorage() {
                @Override
                public long recieveMana(long maxRecieve, float purity) {
                    return TileEntityManaMachine.this.recieveMana(side, maxRecieve, purity);
                }

                @Override
                public long extractMana(long maxExtract) {
                    return 0;
                }

                @Override
                public long getManaCapacity() {
                    return TileEntityManaMachine.this.getManaCapacity();
                }

                @Override
                public long getManaStored() {
                    return TileEntityManaMachine.this.getManaStored();
                }

                @Override
                public boolean canExtract() {
                    return false;
                }

                @Override
                public boolean canRecieve() {
                    return true;
                }

                @Override
                public EnumManaType getManaType() {
                    return TileEntityManaMachine.this.getManaType();
                }

                @Override
                public float getManaPurity() {
                    return TileEntityManaMachine.this.getManaPurity();
                }

                @Override
                public float getPurityModifier() {
                    return TileEntityManaMachine.this.getPurityModifier();
                }
            });
        }

        return super.getCapability(capability,side);
    }
}

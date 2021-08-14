package coda.goatskitchen.common.containers;

import coda.goatskitchen.common.containers.slot.BlenderBottleSlot;
import coda.goatskitchen.common.init.GKBlocks;
import coda.goatskitchen.common.init.GKContainers;
import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class BlenderContainer extends Container {
    public final BlenderTileEntity te;
    private final IWorldPosCallable worldPosCallable;

    public BlenderContainer(final int windowId, final PlayerInventory playerInventory, BlenderTileEntity te) {
        super(GKContainers.BLENDER_CONTAINER.get(), windowId);
        this.te = te;
        this.worldPosCallable = IWorldPosCallable.create(Objects.requireNonNull(te.getLevel()), te.getBlockPos());

        // Tile Entity
        this.addSlot(new Slot(te, 0, 14, 17));
        this.addSlot(new Slot(te, 1, 32, 17));
        this.addSlot(new Slot(te, 2, 50, 17));
        this.addSlot(new Slot(te, 3, 14, 35));
        this.addSlot(new Slot(te, 4, 32, 35));
        this.addSlot(new Slot(te, 5, 50, 35));
        this.addSlot(new Slot(te, 6, 14, 53));
        this.addSlot(new Slot(te, 7, 32, 53));
        this.addSlot(new Slot(te, 8, 50, 53));

        this.addSlot(new BlenderBottleSlot((IInventory) te, 9, 89, 35));

        this.addSlot(new FurnaceResultSlot(playerInventory.player, (IInventory) te, 10, 141, 35));

        // Main Player Inv
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

    }

    public BlenderContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static BlenderTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player Inventory cannot be null");
        Objects.requireNonNull(data, "Packet Buffer cannot be null");
        final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());

        if (te instanceof BlenderTileEntity) {
            return (BlenderTileEntity) te;
        }

        throw new IllegalStateException("Tile Entity is not correct");
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(worldPosCallable, playerIn, GKBlocks.BLENDER.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (index < BlenderTileEntity.slots) {
                if (!this.moveItemStackTo(stack1, 0, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(stack1, 0, 10, true)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return stack;
    }
}

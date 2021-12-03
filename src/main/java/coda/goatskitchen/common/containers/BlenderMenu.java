package coda.goatskitchen.common.containers;

import coda.goatskitchen.common.containers.slot.BlenderBottleSlot;
import coda.goatskitchen.registry.GKBlocks;
import coda.goatskitchen.registry.GKContainers;
import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class BlenderMenu extends AbstractContainerMenu {
    public final BlenderTileEntity te;
    private final ContainerLevelAccess worldPosCallable;

    public BlenderMenu(final int windowId, final Inventory playerInventory, BlenderTileEntity te) {
        super(GKContainers.BLENDER_CONTAINER.get(), windowId);
        this.te = te;
        this.worldPosCallable = ContainerLevelAccess.create(Objects.requireNonNull(te.getLevel()), te.getBlockPos());

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

        this.addSlot(new BlenderBottleSlot(te, 9, 89, 35));

        this.addSlot(new FurnaceResultSlot(playerInventory.player, te, 10, 141, 35));

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

    public BlenderMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static BlenderTileEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "Player Inventory cannot be null");
        Objects.requireNonNull(data, "Packet Buffer cannot be null");
        final BlockEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());

        if (te instanceof BlenderTileEntity) {
            return (BlenderTileEntity) te;
        }

        throw new IllegalStateException("Tile Entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(worldPosCallable, playerIn, GKBlocks.BLENDER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
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

package coda.goatskitchen.containers.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlenderBottleSlot extends Slot {

    public BlenderBottleSlot(IInventory inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
    }

    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == Items.GLASS_BOTTLE;
    }

    public int getItemStackLimit(ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
}

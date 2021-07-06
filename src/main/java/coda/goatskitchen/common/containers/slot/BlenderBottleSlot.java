package coda.goatskitchen.common.containers.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlenderBottleSlot extends Slot {

    public BlenderBottleSlot(IInventory inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
    }

    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() == Items.GLASS_BOTTLE;
    }

    public int getMaxStackSize(ItemStack stack) {
        return super.getMaxStackSize(stack);
    }
}

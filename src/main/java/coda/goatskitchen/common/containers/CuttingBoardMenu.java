package coda.goatskitchen.common.containers;

import coda.goatskitchen.common.init.GKContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nullable;

public class CuttingBoardMenu extends AbstractContainerMenu {
    public CuttingBoardMenu(int windowId, Inventory inventory, FriendlyByteBuf data) {
        this(windowId);
    }

    public CuttingBoardMenu(int windowId) {
        this(GKContainers.CUTTING_BOARD_CONTAINER.get(), windowId);
    }

    public CuttingBoardMenu(@Nullable MenuType<?> type, int windowId) {
        super(type, windowId);

        //addSlot(new Slot(this, 0, ));
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}

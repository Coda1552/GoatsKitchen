package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.BlenderContainer;
import coda.goatskitchen.common.containers.CuttingBoardMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKContainers {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<MenuType<BlenderContainer>> BLENDER_CONTAINER = REGISTER.register("blender", () -> IForgeContainerType.create(BlenderContainer::new));
    public static final RegistryObject<MenuType<CuttingBoardMenu>> CUTTING_BOARD_CONTAINER = REGISTER.register("cutting_board", () -> IForgeContainerType.create(CuttingBoardMenu::new));
}

package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.BlenderMenu;
import coda.goatskitchen.common.containers.CuttingBoardMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKContainers {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<MenuType<BlenderMenu>> BLENDER_CONTAINER = REGISTER.register("blender", () -> IForgeMenuType.create(BlenderMenu::new));
    public static final RegistryObject<MenuType<CuttingBoardMenu>> CUTTING_BOARD_CONTAINER = REGISTER.register("cutting_board", () -> IForgeMenuType.create(CuttingBoardMenu::new));
}

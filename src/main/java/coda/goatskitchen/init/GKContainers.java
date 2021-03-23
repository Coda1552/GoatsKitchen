package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.containers.BlenderContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKContainers {
    public static final DeferredRegister<ContainerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<ContainerType<BlenderContainer>> BLENDER_CONTAINER = REGISTRY.register("blender", () -> IForgeContainerType.create(BlenderContainer::new));

}

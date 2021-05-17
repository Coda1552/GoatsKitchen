package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.entities.ChefEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<EntityType<ChefEntity>> CHEF = create("chef", EntityType.Builder.create(ChefEntity::new, EntityClassification.CREATURE).size(0.3f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTRY.register(name, () -> builder.build(GoatsKitchen.MOD_ID + "." + name));
    }
}

package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.entities.ChefEntity;
import coda.goatskitchen.common.entities.LonghornEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<EntityType<ChefEntity>> CHEF = create("chef", EntityType.Builder.of(ChefEntity::new, MobCategory.CREATURE).sized(0.55f, 0.8f));
    public static final RegistryObject<EntityType<LonghornEntity>> LONGHORN = create("longhorn", EntityType.Builder.of(LonghornEntity::new, MobCategory.CREATURE).sized(0.85f, 1.0f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(GoatsKitchen.MOD_ID + "." + name));
    }
}

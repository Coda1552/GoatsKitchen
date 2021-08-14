package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.entities.ChefEntity;
import coda.goatskitchen.common.entities.LonghornEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<EntityType<ChefEntity>> CHEF = create("chef", EntityType.Builder.of(ChefEntity::new, EntityClassification.CREATURE).sized(0.55f, 0.8f));
    public static final RegistryObject<EntityType<LonghornEntity>> LONGHORN = create("longhorn", EntityType.Builder.of(LonghornEntity::new, EntityClassification.CREATURE).sized(1.55f, 1.8f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(GoatsKitchen.MOD_ID + "." + name));
    }
}

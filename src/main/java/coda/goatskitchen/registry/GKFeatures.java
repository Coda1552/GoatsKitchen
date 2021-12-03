package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.feature.FilletstoneClusterConfiguration;
import coda.goatskitchen.common.world.feature.FilletstoneClusterFeature;
import coda.goatskitchen.common.world.tree.LawyersWigMushroomFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, GoatsKitchen.MOD_ID);

    public static final Feature<FilletstoneClusterConfiguration> FILLETSTONE_CLUSTER = new FilletstoneClusterFeature();

    public static final RegistryObject<LawyersWigMushroomFeature> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", LawyersWigMushroomFeature::new);
}

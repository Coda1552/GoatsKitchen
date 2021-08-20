package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.tree.LawyersWigMushroomFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<LawyersWigMushroomFeature> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", LawyersWigMushroomFeature::new);
}

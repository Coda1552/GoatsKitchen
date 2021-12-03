package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.feature.FilletstoneClusterConfiguration;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class GKConfiguredFeatures {
    public static ConfiguredFeature<?, ?> CONFIGURED_FILLETSTONE_CLUSTER;

    public static void register() {
        CONFIGURED_FILLETSTONE_CLUSTER = FeatureUtils.register(GoatsKitchen.MOD_ID + ":" + "configured_filletstone_cluster", GKFeatures.FILLETSTONE_CLUSTER.configured(new FilletstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8)));
    }
}

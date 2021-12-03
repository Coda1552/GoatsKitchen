package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.feature.FilletstoneClusterConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

public class GKConfiguredFeatures {
    private static final FilletstoneClusterConfiguration CONFIG = new FilletstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8);

    public static ConfiguredFeature<?, ?> CONFIGURED_FILLETSTONE_CLUSTER = FeatureUtils.register(GoatsKitchen.MOD_ID + ":" + "configured_filletstone_cluster", Feature.ORE.configured(new OreConfiguration(OreFeatures.NATURAL_STONE, Blocks.RED_SANDSTONE.defaultBlockState(), 64)));

    public static PlacedFeature PLACED_FILLETSTONE_CLUSTER = CONFIGURED_FILLETSTONE_CLUSTER.placed(CountPlacement.of(UniformInt.of(128, 256)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

    public static List<PlacedFeature> GK_FEATURES = new ArrayList<>();

    public static void registerPlacedFeatures() {
        Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;

        GK_FEATURES.add(Registry.register(registry, new ResourceLocation(GoatsKitchen.MOD_ID, "filletstone_cluster"), PLACED_FILLETSTONE_CLUSTER));
    }

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, new ResourceLocation(GoatsKitchen.MOD_ID, "filletstone_cluster"), CONFIGURED_FILLETSTONE_CLUSTER);
    }
}

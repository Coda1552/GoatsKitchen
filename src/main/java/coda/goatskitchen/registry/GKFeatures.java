package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.feature.FilletstoneClusterConfiguration;
import coda.goatskitchen.common.world.feature.FilletstoneClusterFeature;
import coda.goatskitchen.common.world.tree.LawyersWigMushroomFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<FilletstoneClusterFeature> FILLETSTONE_CLUSTER = REGISTER.register("filletstone_cluster", FilletstoneClusterFeature::new);

    public static ConfiguredFeature<?, ?> CONFIGURED_FILLETSTONE_CLUSTER;

    public static void register() {
        CONFIGURED_FILLETSTONE_CLUSTER = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, GoatsKitchen.MOD_ID + ":" + "filletstone_cluster", FILLETSTONE_CLUSTER.get().configured(new FilletstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8)));
    }

    public static final RegistryObject<LawyersWigMushroomFeature> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", LawyersWigMushroomFeature::new);
/*
    public static final FilletstoneClusterFeature FILLETSTONE_CLUSTER_FEATURE = new FilletstoneClusterFeature();

    //public static final RegistryObject<FilletstoneClusterFeature> FILLETSTONE_CLUSTER = REGISTER.register("filletstone_cluster", FilletstoneClusterFeature::new);

    // todo fix pineapple registry
    //public static final ConfiguredFeature<?, ?> PATCH_PINEAPPLE = register("patch_pineapple", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(GKBlocks.PINEAPPLE.get().defaultBlockState().setValue(PineapplePlantBlock.AGE, new Random().nextInt(5))), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.defaultBlockState().getBlock())).canReplace().noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<FilletstoneClusterConfiguration, ?> FILLETSTONE_CLUSTER_CONFIG = register("filletstone_cluster_config", FILLETSTONE_CLUSTER_FEATURE.configured(new FilletstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8)));

    public static final PlacedFeature FILLETSTONE_CLUSTER_PLACED = Registry.register(BuiltinRegistries.PLACED_FEATURE, GoatsKitchen.MOD_ID + ":" + "filletstone_cluster", FILLETSTONE_CLUSTER_CONFIG.placed(CountPlacement.of(UniformInt.of(48, 96)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, GoatsKitchen.MOD_ID + ":" + name, configuredFeature);
    }*/
}

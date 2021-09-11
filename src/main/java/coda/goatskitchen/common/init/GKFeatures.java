package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.blocks.PineapplePlantBlock;
import coda.goatskitchen.common.world.tree.LawyersWigMushroomFeature;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class GKFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<LawyersWigMushroomFeature> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", LawyersWigMushroomFeature::new);

    // todo fix pineapple registry
    //public static final ConfiguredFeature<?, ?> PATCH_PINEAPPLE = register("patch_pineapple", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(GKBlocks.PINEAPPLE.get().defaultBlockState().setValue(PineapplePlantBlock.AGE, new Random().nextInt(5))), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.defaultBlockState().getBlock())).canReplace().noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}

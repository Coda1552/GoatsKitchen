package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.items.GKSpawnEggItem;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, GoatsKitchen.MOD_ID);

    // Food
    public static final RegistryObject<Item> CHEESE = REGISTER.register("cheese", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().saturationMod(0.3f).nutrition(3).build())));
    public static final RegistryObject<Item> WINE_BOTTLE = REGISTER.register("wine_bottle", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<Item> SWEET_BERRY_JELLO = REGISTER.register("sweet_berry_jello", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().saturationMod(0.5f).nutrition(6).build())));
    public static final RegistryObject<Item> PINEAPPLE_SEEDS = REGISTER.register("pineapple_seeds", () -> new BlockNamedItem(GKBlocks.PINEAPPLE.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<Item> PINEAPPLE = REGISTER.register("pineapple", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().saturationMod(0.35f).nutrition(2).build())));

    // Other
    public static final RegistryObject<Item> CHEF_SPAWN_EGG = REGISTER.register("chef_spawn_egg", () -> new GKSpawnEggItem(GKEntities.CHEF, 0x9f8484, 0x473a3a, new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<Item> LONGHORN_SPAWN_EGG = REGISTER.register("longhorn_spawn_egg", () -> new GKSpawnEggItem(GKEntities.LONGHORN, 0xd7bd9b, 0x774634, new Item.Properties().tab(GoatsKitchen.GROUP)));

    // Blocks
    public static final RegistryObject<BlockItem> BLENDER = REGISTER.register("blender", () -> new BlockItem(GKBlocks.BLENDER.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<BlockItem> LAWYERS_WIG_MUSHROOM = REGISTER.register("lawyers_wig_mushroom", () -> new BlockItem(GKBlocks.LAWYERS_WIG_MUSHROOM.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<BlockItem> LAWYERS_WIG_MUSHROOM_BLOCK = REGISTER.register("lawyers_wig_mushroom_block", () -> new BlockItem(GKBlocks.LAWYERS_WIG_MUSHROOM_BLOCK.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
}

package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.items.GKSpawnEggItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, GoatsKitchen.MOD_ID);

    // Food
    public static final RegistryObject<Item> POPPED_WEE_WEE = REGISTER.register("popped_wee_wee", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().fast().saturationMod(0.2f).nutrition(2).build())));
    public static final RegistryObject<Item> CHEESE = REGISTER.register("cheese", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().saturationMod(0.3f).nutrition(3).build())));
    public static final RegistryObject<Item> WINE_BOTTLE = REGISTER.register("wine_bottle", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<Item> PINEAPPLE_SEEDS = REGISTER.register("pineapple_seeds", () -> new BlockNamedItem(GKBlocks.PINEAPPLE_STEM.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<Item> SWEET_BERRY_JELLO = REGISTER.register("sweet_berry_jello", () -> new Item(new Item.Properties().tab(GoatsKitchen.GROUP).food(new Food.Builder().saturationMod(0.5f).nutrition(6).build())));

    // Other
    public static final RegistryObject<Item> CHEF_SPAWN_EGG = REGISTER.register("chef_spawn_egg", () -> new GKSpawnEggItem(GKEntities.CHEF, 0x9f8484, 0x473a3a, new Item.Properties().tab(GoatsKitchen.GROUP)));

    // Blocks
    public static final RegistryObject<BlockItem> BLENDER = REGISTER.register("blender", () -> new BlockItem(GKBlocks.BLENDER.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
    public static final RegistryObject<BlockItem> PINEAPPLE = REGISTER.register("pineapple", () -> new BlockItem(GKBlocks.PINEAPPLE.get(), new Item.Properties().tab(GoatsKitchen.GROUP)));
}

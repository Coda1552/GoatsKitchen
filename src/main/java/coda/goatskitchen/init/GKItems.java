package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<BlockItem> BLENDER = REGISTRY.register("blender", () -> new BlockItem(GKBlocks.BLENDER.get(), new Item.Properties().group(GoatsKitchen.GROUP)));
}

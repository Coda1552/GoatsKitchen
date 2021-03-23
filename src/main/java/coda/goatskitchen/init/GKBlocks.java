package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.blocks.BlenderBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<Block> BLENDER = REGISTRY.register("blender", () -> new BlenderBlock(AbstractBlock.Properties.create(Material.GLASS).harvestTool(ToolType.AXE).hardnessAndResistance(1.5f).notSolid().sound(SoundType.GLASS)));
}

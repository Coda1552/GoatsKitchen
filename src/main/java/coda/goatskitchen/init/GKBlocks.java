package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.blocks.BlenderBlock;
import coda.goatskitchen.common.blocks.PineappleBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<Block> BLENDER = REGISTER.register("blender", () -> new BlenderBlock(AbstractBlock.Properties.of(Material.GLASS).harvestTool(ToolType.AXE).strength(1.5f).noOcclusion().sound(SoundType.GLASS)));
    public static final RegistryObject<Block> PINEAPPLE = REGISTER.register("pineapple", () -> new PineappleBlock(AbstractBlock.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_YELLOW).strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PINEAPPLE_STEM = REGISTER.register("pineapple_stem", () -> new StemBlock((StemGrownBlock) PINEAPPLE.get(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> ATTACHED_PINEAPPLE_STEM = REGISTER.register("attached_pineapple_stem", () -> new AttachedStemBlock((StemGrownBlock) PINEAPPLE.get(), AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.WOOD)));
}
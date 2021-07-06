package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKTileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<TileEntityType<BlenderTileEntity>> BLENDER_TILE_ENTITY = REGISTER.register("blender", () -> TileEntityType.Builder.of(BlenderTileEntity::new, GKBlocks.BLENDER.get()).build(null));
}

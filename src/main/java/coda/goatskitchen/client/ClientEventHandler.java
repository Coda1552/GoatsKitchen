package coda.goatskitchen.client;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.screen.BlenderScreen;
import coda.goatskitchen.client.ter.BlenderTileEntityRenderer;
import coda.goatskitchen.init.GKBlocks;
import coda.goatskitchen.init.GKContainers;
import coda.goatskitchen.init.GKTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GoatsKitchen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(GKContainers.BLENDER_CONTAINER.get(), BlenderScreen::new);
        RenderTypeLookup.setRenderLayer(GKBlocks.BLENDER.get(), RenderType.getCutout());
        ClientRegistry.bindTileEntityRenderer(GKTileEntities.BLENDER_TILE_ENTITY.get(), BlenderTileEntityRenderer::new);
    }
}

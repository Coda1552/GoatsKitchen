package coda.goatskitchen.client;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.renderer.ChefRenderer;
import coda.goatskitchen.client.renderer.LonghornRenderer;
import coda.goatskitchen.client.screen.BlenderScreen;
import coda.goatskitchen.client.ter.BlenderTileEntityRenderer;
import coda.goatskitchen.common.init.GKBlocks;
import coda.goatskitchen.common.init.GKContainers;
import coda.goatskitchen.common.init.GKEntities;
import coda.goatskitchen.common.init.GKTileEntities;
import coda.goatskitchen.common.items.GKSpawnEggItem;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GoatsKitchen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(GKContainers.BLENDER_CONTAINER.get(), BlenderScreen::new);
        RenderTypeLookup.setRenderLayer(GKBlocks.BLENDER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(GKBlocks.PINEAPPLE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(GKBlocks.POTTED_LAWYERS_WIG_MUSHROOM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(GKBlocks.LAWYERS_WIG_MUSHROOM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(GKBlocks.LAWYERS_WIG_MUSHROOM_BLOCK.get(), RenderType.cutout());

        ClientRegistry.bindTileEntityRenderer(GKTileEntities.BLENDER_TILE_ENTITY.get(), BlenderTileEntityRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(GKEntities.CHEF.get(), ChefRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GKEntities.LONGHORN.get(), LonghornRenderer::new);
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((GKSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (GKSpawnEggItem e : GKSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}

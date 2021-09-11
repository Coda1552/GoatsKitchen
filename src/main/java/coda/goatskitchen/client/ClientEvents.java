package coda.goatskitchen.client;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.model.ChefModel;
import coda.goatskitchen.client.model.LonghornModel;
import coda.goatskitchen.client.renderer.ChefRenderer;
import coda.goatskitchen.client.renderer.LonghornRenderer;
import coda.goatskitchen.client.screen.BlenderScreen;
import coda.goatskitchen.client.screen.CuttingBoardScreen;
import coda.goatskitchen.client.ter.BlenderTileEntityRenderer;
import coda.goatskitchen.common.init.GKBlocks;
import coda.goatskitchen.common.init.GKContainers;
import coda.goatskitchen.common.init.GKEntities;
import coda.goatskitchen.common.init.GKTileEntities;
import coda.goatskitchen.common.items.GKSpawnEggItem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = GoatsKitchen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    private static ShaderInstance cuttingShader;

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(GKContainers.BLENDER_CONTAINER.get(), BlenderScreen::new);
        MenuScreens.register(GKContainers.CUTTING_BOARD_CONTAINER.get(), CuttingBoardScreen::new);

        ItemBlockRenderTypes.setRenderLayer(GKBlocks.BLENDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GKBlocks.PINEAPPLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GKBlocks.POTTED_LAWYERS_WIG_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GKBlocks.LAWYERS_WIG_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GKBlocks.LAWYERS_WIG_MUSHROOM_BLOCK.get(), RenderType.cutout());

        BlockEntityRenderers.register(GKTileEntities.BLENDER_TILE_ENTITY.get(), BlenderTileEntityRenderer::new);

        EntityRenderers.register(GKEntities.CHEF.get(), ChefRenderer::new);
        EntityRenderers.register(GKEntities.LONGHORN.get(), LonghornRenderer::new);

        ForgeHooksClient.registerLayerDefinition(LonghornRenderer.MODEL_LAYER, LonghornModel::createLayer);
        ForgeHooksClient.registerLayerDefinition(ChefRenderer.MODEL_LAYER, ChefModel::createLayer);
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((GKSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (GKSpawnEggItem e : GKSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }

    @SubscribeEvent
    public static void reloadShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(GoatsKitchen.MOD_ID, "cutting_board"), DefaultVertexFormat.POSITION_COLOR_TEX), shader -> cuttingShader = shader);
    }

    public static ShaderInstance getCuttingShader() {
        return cuttingShader;
    }
}

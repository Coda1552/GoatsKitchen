package coda.goatskitchen.client;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.renderer.ChefRenderer;
import coda.goatskitchen.client.screen.BlenderScreen;
import coda.goatskitchen.client.ter.BlenderTileEntityRenderer;
import coda.goatskitchen.common.items.GKSpawnEggItem;
import coda.goatskitchen.init.GKBlocks;
import coda.goatskitchen.init.GKContainers;
import coda.goatskitchen.init.GKEntities;
import coda.goatskitchen.init.GKTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
        RenderTypeLookup.setRenderLayer(GKBlocks.PINEAPPLE_STEM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(GKBlocks.ATTACHED_PINEAPPLE_STEM.get(), RenderType.cutout());
        ClientRegistry.bindTileEntityRenderer(GKTileEntities.BLENDER_TILE_ENTITY.get(), BlenderTileEntityRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(GKEntities.CHEF.get(), ChefRenderer::new);
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((GKSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (GKSpawnEggItem e : GKSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }

    @SubscribeEvent
    public static void blockColors(ColorHandlerEvent.Block event) {
        BlockColors handler = event.getBlockColors();
        // handler.register((p_228059_0_, p_228059_1_, p_228059_2_, p_228059_3_) -> getPineappleStemColor(), GKBlocks.ATTACHED_PINEAPPLE_STEM.get());
        // handler.register((p_228059_0_, p_228059_1_, p_228059_2_, p_228059_3_) -> getPineappleStemColor(), GKBlocks.PINEAPPLE_STEM.get());
    }

    @OnlyIn(Dist.CLIENT)
    public static int getPineappleStemColor() {
        return MathHelper.color(65, 95, 39);
    }
}

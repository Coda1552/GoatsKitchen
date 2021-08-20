package coda.goatskitchen.client.ter;

import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

public class BlenderTileEntityRenderer implements BlockEntityRenderer<BlenderTileEntity> {
    private static final float[][] TRANSFORMATIONS = new float[][] {
            {0.5f, 0.25f, 0.4f, 0},
            {0.4f, 0.25f, 0.5f, 90},
            {0.5f, 0.25f, 0.6f, 0},
            {0.6f, 0.25f, 0.5f, 90},
            {0.65f, 0.45f, 0.58f, 72},
            {0.6f, 0.45f, 0.4f, 144},
            {0.4f, 0.45f, 0.4f, 216},
            {0.35f, 0.45f, 0.58f, 288},
            {0.5f, 0.45f, 0.65f, 0}
    };

    private final Minecraft mc = Minecraft.getInstance();

    public BlenderTileEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BlenderTileEntity te, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int p_112312_) {
        for (int i = 0; i < te.getContainerSize() - 2; i++) {
            final float[] transformation = TRANSFORMATIONS[i];
            final ItemStack stack = te.getItem(i);
            matrixStack.pushPose();
            matrixStack.translate(transformation[0], transformation[1], transformation[2]);
            matrixStack.mulPose(Vector3f.YN.rotationDegrees((transformation[3] + te.blendingTicks * 15) % 360));
            matrixStack.scale(0.65f, 0.65f, 0.65f);

            BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 0);
            mc.getItemRenderer().render(stack, ItemTransforms.TransformType.GROUND, true, matrixStack, bufferIn, combinedLightIn, p_112312_, model);
            matrixStack.popPose();
        }
    }
}

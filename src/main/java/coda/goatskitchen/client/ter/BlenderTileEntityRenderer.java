package coda.goatskitchen.client.ter;

import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class BlenderTileEntityRenderer extends TileEntityRenderer<BlenderTileEntity> {
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

    public BlenderTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlenderTileEntity te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        for (int i = 0; i < te.getContainerSize() - 2; i++) {
            final float[] transformation = TRANSFORMATIONS[i];
            final ItemStack stack = te.getItem(i);
            matrixStack.pushPose();
            matrixStack.translate(transformation[0], transformation[1], transformation[2]);
            matrixStack.mulPose(Vector3f.YN.rotationDegrees((transformation[3] + te.blendingTicks * 15) % 360));
            matrixStack.scale(0.65f, 0.65f, 0.65f);

            IBakedModel model = mc.getItemRenderer().getModel(stack, null, null);
            mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, bufferIn, combinedLightIn, combinedOverlayIn, model);
            matrixStack.popPose();
        }
    }
}

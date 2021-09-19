package coda.goatskitchen.client.screen;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.CuttingBoardMenu;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class CuttingBoardScreen extends AbstractContainerScreen<CuttingBoardMenu> {
    private static final FruitCuttingRenderer CUT_RENDERER = new FruitCuttingRenderer();
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/gui/cutting_board.png");

    private final Vector3f[] mousePoints = new Vector3f[16];
    private int currentIndex;

    private double cutStartX = -1;
    private double cutStartY = -1;

    private int fruitX = 48;
    private int fruitY = 5;
    private int fruitWidth = 48;
    private int fruitHeight = 73;
    private int cuts;

    public CuttingBoardScreen(CuttingBoardMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    private int next() {
        return currentIndex++ % 16;
    }

    @Override
    protected void init() {
        super.init();
        // TODO move
        CUT_RENDERER.begin(new ResourceLocation(GoatsKitchen.MOD_ID, "textures/gui/pineapple_cutting_item.png"), fruitWidth, fruitHeight);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        Matrix4f pose = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        CUT_RENDERER.bind();
        float u = fruitWidth / 256f;
        float v = fruitHeight / 256f;
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(pose, i + fruitX + fruitWidth, j + fruitY + fruitHeight, 0).uv(u, 0).endVertex();
        bufferbuilder.vertex(pose, i + fruitX + fruitWidth, j + fruitY, 0).uv(u, v).endVertex();
        bufferbuilder.vertex(pose, i + fruitX, j + fruitY, 0).uv(0, v).endVertex();
        bufferbuilder.vertex(pose, i + fruitX, j + fruitY + fruitHeight, 0).uv(0, 0).endVertex();
        tesselator.end();

        float cutRed = 0f;
        float cutGreen = 0f;
        float cutBlue = 0f;

        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        for (int k = 0; k < mousePoints.length; ++k) {
            Vector3f mousePoint = mousePoints[k];
            if (mousePoint != null) {
                mousePoint.setZ(mousePoint.z() + 1);
                if (Util.getMillis() - mousePoint.z() > 150) mousePoints[k] = null;
            }
        }

        int startingPoint = currentIndex;
        for (int k = 0; k < 15; k++) {
            int index = (startingPoint + k) % 16;
            int nextIndex = (index + 1) % 16;
            if (mousePoints[index] != null) {
                if (mousePoints[nextIndex] != null) {
                    float t = k / 16f;
                    float angle = (float) (Mth.atan2(mousePoints[nextIndex].y() - mousePoints[index].y(), mousePoints[nextIndex].x() - mousePoints[index].x()) + Math.PI * 0.5);
                    float centerDistance = 1 - Mth.abs(t - 0.5f) * 2;
                    float fadeAmount = centerDistance * centerDistance * centerDistance;
                    float orthoX = Mth.cos(angle) * 0.5f * fadeAmount;
                    float orthoY = Mth.sin(angle) * 0.5f * fadeAmount;
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                    bufferbuilder.vertex(pose, mousePoints[index].x() - orthoX, mousePoints[index].y() - orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[index].x() + orthoX, mousePoints[index].y() + orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[nextIndex].x() + orthoX, mousePoints[nextIndex].y() + orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[nextIndex].x() - orthoX, mousePoints[nextIndex].y() - orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    tesselator.end();
                }
            } else if (mousePoints[nextIndex] != null) {
                mousePoints[nextIndex].setZ(mousePoints[nextIndex].z() + 1);
            }
        }
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            cutStartX = mouseX;
            cutStartY = mouseY;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    // Note, I hate how many params are here, but it's the best way to do this without allocating vectors
    /**
     * Check for the intersection and hit vectors of 2 lines.
     * @param line1X1 Line 1 X start point
     * @param line1Y1 Line 1 Y start point
     * @param line1X2 Line 1 X end point
     * @param line1Y2 Line 1 Y end point
     * @param line2X1 Line 2 X start point
     * @param line2Y1 Line 2 Y start point
     * @param line2X2 Line 2 X end point
     * @param line2Y2 Line 2 Y end point
     * @return A vector containing the hit coordinates
     */
    private static Vector3d linesIntersect(
            double line1X1, double line1Y1, double line1X2, double line1Y2,
            double line2X1, double line2Y1, double line2X2, double line2Y2
    ) {

        // This math is copied, I've no clue what it really means lol
        double direction = (line2Y2 - line2Y1) * (line1X2 - line1X1) - (line2X2 - line2X1) * (line1Y2 - line1Y1);
        double pointA = ((line2X2 - line2X1) * (line1Y1 - line2Y1) - (line2Y2 - line2Y1) * (line1X1 - line2X1)) / direction;
        double pointB = ((line1X2 - line1X1) * (line1Y1 - line2Y1) - (line1Y2 - line1Y1) * (line1X1 - line2X1)) / direction;

        if (pointA >= 0 && pointA <= 1 && pointB >= 0 && pointB <= 1) {
            double intersectionX = line1X1 + (pointA * (line1X2 - line1X1));
            double intersectionY = line1Y1 + (pointA * (line1Y2 - line1Y1));
            return new Vector3d(intersectionX, intersectionY, 0);
        }
        return null;
    }

    private int add(int index, Vector3d[] points, Vector3d toAdd) {
        if (toAdd != null) {
            points[index] = toAdd;
            return ++index;
        }
        return index;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && cutStartX >= 0 && cutStartY >= 0) {
            if (cuts < 5) {
                int i = this.leftPos;
                int j = (this.height - this.imageHeight) / 2;
                int index = 0;
                Vector3d[] points = new Vector3d[2]; // Start and end points as array
                if (cutStartX > i + fruitX && cutStartY > j + fruitY && cutStartX < i + fruitX + fruitWidth && cutStartY < j + fruitY + fruitHeight) {
                    index = 1;
                    points[0] = new Vector3d(cutStartX - i, cutStartY - j, 0);
                }

                if (mouseX > i + fruitX && mouseY > j + fruitY && mouseX < i + fruitX + fruitWidth && mouseY < j + fruitY + fruitHeight) {
                    if (index == 1) index = 2;
                    points[1] = new Vector3d(cutStartX - i, cutStartY - j, 0);
                }

                if (index < 2) {
                    // Rectangle collision
                    index = add(index, points, linesIntersect(cutStartX - i, cutStartY - j, mouseX - i, mouseY - j, fruitX, fruitY + fruitHeight, fruitX + fruitWidth, fruitY + fruitHeight)); // Up
                    index = add(index, points, linesIntersect(cutStartX - i, cutStartY - j, mouseX - i, mouseY - j, fruitX, fruitY, fruitX + fruitWidth, fruitY)); // Down
                    index = add(index, points, linesIntersect(cutStartX - i, cutStartY - j, mouseX - i, mouseY - j, fruitX, fruitY, fruitX, fruitY + fruitHeight)); // Left
                    add(index, points, linesIntersect(cutStartX - i, cutStartY - j, mouseX - i, mouseY - j, fruitX + fruitWidth, fruitY, fruitX + fruitWidth, fruitY + fruitHeight)); // Right
                }

                // If the end point is not null, neither are
                if (points[1] != null) {
                    float xPos = (float) (points[0].x + points[1].x) * 0.5f;
                    float yPos = (float) (points[0].y + points[1].y) * 0.5f;
                    if (xPos > fruitX && yPos > fruitY && xPos < fruitX + fruitWidth && yPos < fruitY + fruitHeight) {
                        CUT_RENDERER.addCut((xPos - fruitX) / fruitWidth, (yPos - fruitY) / fruitHeight, (float) -Mth.atan2(mouseY - cutStartY, mouseX - cutStartX));
                        ++cuts;
                    }
                }
            }
            cutStartX = -1;
            cutStartY = -1;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int p_97754_, double p_97755_, double p_97756_) {
        int next = next();
        Vector3f mousePoint = mousePoints[next];
        if (mousePoint == null) {
            mousePoint = new Vector3f((float) mouseX, (float) mouseY, Util.getMillis());
            mousePoints[next] = mousePoint;
        } else {
            mousePoint.setX((float) mouseX);
            mousePoint.setY((float) mouseY);
            mousePoint.setZ(Util.getMillis());
        }

        return super.mouseDragged(mouseX, mouseY, p_97754_, p_97755_, p_97756_);
    }
}

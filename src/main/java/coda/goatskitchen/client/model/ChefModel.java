package coda.goatskitchen.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChefModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer footRight;
    public ModelRenderer footLeft;
    public ModelRenderer head;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer tailBase;
    public ModelRenderer hatBase;
    public ModelRenderer earRight;
    public ModelRenderer earLeft;
    public ModelRenderer hatTop;
    public ModelRenderer spatulaBase;
    public ModelRenderer spatulaHead;
    public ModelRenderer tailTip;

    public ChefModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.earLeft = new ModelRenderer(this, 53, 24);
        this.earLeft.setPos(1.0F, -1.5F, 0.0F);
        this.earLeft.addBox(0.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tailBase = new ModelRenderer(this, 1, 9);
        this.tailBase.setPos(0.0F, 2.0F, 1.5F);
        this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailBase, -0.27366763203903305F, 0.0F, 0.0F);
        this.hatBase = new ModelRenderer(this, 19, 15);
        this.hatBase.setPos(0.0F, -2.0F, -0.5F);
        this.hatBase.addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hatBase, -0.35185837453889574F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 50, 7);
        this.armLeft.setPos(2.5F, -3.5F, -0.5F);
        this.armLeft.addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, 0.0F, 1.1728612040769677F);
        this.body = new ModelRenderer(this, 0, 18);
        this.body.setPos(0.0F, 19.5F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.5F, 6.0F, 8.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 40, 22);
        this.head.setPos(0.0F, -4.0F, 0.5F);
        this.head.addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.footRight = new ModelRenderer(this, 25, 25);
        this.footRight.mirror = true;
        this.footRight.setPos(-2.5F, 3.5F, 2.5F);
        this.footRight.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(footRight, 0.0F, 0.2617993877991494F, 0.0F);
        this.hatTop = new ModelRenderer(this, 36, 14);
        this.hatTop.setPos(0.0F, -5.0F, 0.0F);
        this.hatTop.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.spatulaHead = new ModelRenderer(this, 27, 5);
        this.spatulaHead.setPos(0.0F, -7.0F, 0.0F);
        this.spatulaHead.addBox(-2.5F, -5.0F, -0.5F, 5.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tailTip = new ModelRenderer(this, 12, 7);
        this.tailTip.setPos(0.0F, 0.0F, 5.5F);
        this.tailTip.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailTip, 0.19547687289441354F, 0.0F, 0.0F);
        this.spatulaBase = new ModelRenderer(this, 41, 3);
        this.spatulaBase.setPos(3.5F, 0.0F, 0.0F);
        this.spatulaBase.addBox(-1.0F, -7.0F, -0.5F, 2.0F, 7.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(spatulaBase, 1.5708F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 50, 7);
        this.armRight.mirror = true;
        this.armRight.setPos(-2.5F, -3.5F, -0.5F);
        this.armRight.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, 0.0F, 0.0F, -1.1730357742864224F);
        this.earRight = new ModelRenderer(this, 53, 24);
        this.earRight.mirror = true;
        this.earRight.setPos(-1.0F, -1.5F, 0.0F);
        this.earRight.addBox(-2.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.footLeft = new ModelRenderer(this, 25, 25);
        this.footLeft.setPos(2.5F, 3.5F, 2.5F);
        this.footLeft.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(footLeft, 0.0F, -0.2617993877991494F, 0.0F);
        this.head.addChild(this.earLeft);
        this.body.addChild(this.tailBase);
        this.head.addChild(this.hatBase);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.head);
        this.body.addChild(this.footRight);
        this.hatBase.addChild(this.hatTop);
        this.spatulaBase.addChild(this.spatulaHead);
        this.tailBase.addChild(this.tailTip);
        this.armLeft.addChild(this.spatulaBase);
        this.body.addChild(this.armRight);
        this.head.addChild(this.earRight);
        this.body.addChild(this.footLeft);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 0.65f;
        this.body.y = MathHelper.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.1F + 19.5F;
        this.footRight.y = MathHelper.cos(entityIn.tickCount * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.footLeft.y = MathHelper.cos(entityIn.tickCount * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.armRight.zRot = MathHelper.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * 0.25F - 1.2F;
        this.armLeft.zRot = MathHelper.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * -0.25F + 1.2F;
        this.tailBase.xRot = MathHelper.cos(1.5F + entityIn.tickCount * speed * 0.2F) * degree * 0.2F;
        this.tailTip.xRot = MathHelper.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.2F;
        this.head.y = MathHelper.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.05F - 4.0F;
        this.head.xRot = MathHelper.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * 0.1F + 0.02F;
        this.spatulaBase.z = 2.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}

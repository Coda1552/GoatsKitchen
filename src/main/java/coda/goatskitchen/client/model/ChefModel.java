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
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.earLeft = new ModelRenderer(this, 53, 24);
        this.earLeft.setRotationPoint(1.0F, -1.5F, 0.0F);
        this.earLeft.addBox(0.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tailBase = new ModelRenderer(this, 1, 9);
        this.tailBase.setRotationPoint(0.0F, 2.0F, 1.5F);
        this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailBase, -0.27366763203903305F, 0.0F, 0.0F);
        this.hatBase = new ModelRenderer(this, 19, 15);
        this.hatBase.setRotationPoint(0.0F, -2.0F, -0.5F);
        this.hatBase.addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hatBase, -0.35185837453889574F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 50, 7);
        this.armLeft.setRotationPoint(2.5F, -3.5F, -0.5F);
        this.armLeft.addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, 0.0F, 1.1728612040769677F);
        this.body = new ModelRenderer(this, 0, 18);
        this.body.setRotationPoint(0.0F, 19.5F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.5F, 6.0F, 8.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 40, 22);
        this.head.setRotationPoint(0.0F, -4.0F, 0.5F);
        this.head.addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.footRight = new ModelRenderer(this, 25, 25);
        this.footRight.mirror = true;
        this.footRight.setRotationPoint(-2.5F, 3.5F, 2.5F);
        this.footRight.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(footRight, 0.0F, 0.2617993877991494F, 0.0F);
        this.hatTop = new ModelRenderer(this, 36, 14);
        this.hatTop.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.hatTop.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.spatulaHead = new ModelRenderer(this, 27, 5);
        this.spatulaHead.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.spatulaHead.addBox(-2.5F, -5.0F, -0.5F, 5.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tailTip = new ModelRenderer(this, 12, 7);
        this.tailTip.setRotationPoint(0.0F, 0.0F, 5.5F);
        this.tailTip.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailTip, 0.19547687289441354F, 0.0F, 0.0F);
        this.spatulaBase = new ModelRenderer(this, 41, 3);
        this.spatulaBase.setRotationPoint(3.5F, 0.0F, 0.0F);
        this.spatulaBase.addBox(-1.0F, -7.0F, -0.5F, 2.0F, 7.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 50, 7);
        this.armRight.mirror = true;
        this.armRight.setRotationPoint(-2.5F, -3.5F, -0.5F);
        this.armRight.addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, 0.0F, 0.0F, -1.1730357742864224F);
        this.earRight = new ModelRenderer(this, 53, 24);
        this.earRight.mirror = true;
        this.earRight.setRotationPoint(-1.0F, -1.5F, 0.0F);
        this.earRight.addBox(-2.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.footLeft = new ModelRenderer(this, 25, 25);
        this.footLeft.setRotationPoint(2.5F, 3.5F, 2.5F);
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
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.body.rotationPointY = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * 0.1F + 19.5F;
        this.footRight.rotationPointY = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.footLeft.rotationPointY = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.armRight.rotateAngleZ = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * 0.5F - 1.2F;
        this.armLeft.rotateAngleZ = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * -0.5F + 1.2F;
        this.tailBase.rotateAngleX = MathHelper.cos(1.5F + entityIn.ticksExisted * speed * 0.2F) * degree * 0.2F;
        this.tailTip.rotateAngleX = MathHelper.cos(1.5F + entityIn.ticksExisted * speed * 0.2F) * degree * 0.2F;
        this.head.rotationPointY = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * 0.05F - 4.0F;
        this.head.rotateAngleX = MathHelper.cos(entityIn.ticksExisted * speed * 0.2F) * degree * 0.2F + 0.02F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

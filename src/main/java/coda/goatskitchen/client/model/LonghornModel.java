package coda.goatskitchen.client.model;

import coda.goatskitchen.common.entities.LonghornEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class LonghornModel<T extends Entity> extends AgeableModel<LonghornEntity> {
    public ModelRenderer body;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer tail;
    public ModelRenderer chest;
    public ModelRenderer head;
    public ModelRenderer hornRight;
    public ModelRenderer hornLeft;
    public ModelRenderer hornRight_1;
    public ModelRenderer hornLeft_1;

    public LonghornModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 36, 0);
        this.head.setPos(0.0F, -5.0F, -10.5F);
        this.head.addBox(-4.0F, 0.0F, -1.5F, 8.0F, 11.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, -0.9599310885968813F, 0.0F, 0.0F);
        this.hornRight = new ModelRenderer(this, 0, 15);
        this.hornRight.setPos(4.0F, 1.0F, 1.5F);
        this.hornRight.addBox(0.0F, -1.0F, -3.0F, 16.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leftArm = new ModelRenderer(this, 0, 0);
        this.leftArm.setPos(3.5F, 14.0F, -6.0F);
        this.leftArm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 0);
        this.rightLeg.setPos(-3.5F, 14.0F, 7.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.hornLeft = new ModelRenderer(this, 0, 15);
        this.hornLeft.mirror = true;
        this.hornLeft.setPos(-4.0F, 1.0F, 1.5F);
        this.hornLeft.addBox(-16.0F, -1.0F, -3.0F, 16.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.hornLeft_1 = new ModelRenderer(this, 16, 0);
        this.hornLeft_1.mirror = true;
        this.hornLeft_1.setPos(-14.5F, 0.0F, -3.0F);
        this.hornLeft_1.addBox(-1.5F, -1.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 44);
        this.body.setPos(0.0F, 9.0F, 0.0F);
        this.body.addBox(-5.5F, -5.0F, 0.0F, 11.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 44);
        this.tail.setPos(0.0F, -4.0F, 10.0F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, 0.35185837453889574F, 0.0F, 0.0F);
        this.hornRight_1 = new ModelRenderer(this, 16, 0);
        this.hornRight_1.setPos(14.5F, 0.0F, -3.0F);
        this.hornRight_1.addBox(-1.5F, -1.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightArm = new ModelRenderer(this, 0, 0);
        this.rightArm.setPos(-3.5F, 14.0F, -6.0F);
        this.rightArm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 0, 22);
        this.chest.setPos(0.0F, 0.0F, 0.0F);
        this.chest.addBox(-6.5F, -7.0F, -10.0F, 13.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 0);
        this.leftLeg.setPos(3.5F, 14.0F, 7.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.chest.addChild(this.head);
        this.head.addChild(this.hornRight);
        this.head.addChild(this.hornLeft);
        this.hornLeft.addChild(this.hornLeft_1);
        this.body.addChild(this.tail);
        this.hornRight.addChild(this.hornRight_1);
        this.body.addChild(this.chest);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body, leftLeg, rightLeg, leftArm, rightArm);
    }

    @Override
    public void setupAnim(LonghornEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.15f;
        float degree = 1.0f;
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.chest.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.chest.y = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.075F * limbSwingAmount;
        this.head.y = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.15F * limbSwingAmount - 5.0F;
        this.head.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.25F * limbSwingAmount - 0.9F;
        this.tail.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.25F;
        this.leftArm.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.leftArm.y = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.leftLeg.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.leftLeg.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.rightLeg.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.rightLeg.y = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.rightArm.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.rightArm.y = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}

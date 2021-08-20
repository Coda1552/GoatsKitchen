package coda.goatskitchen.client.model;

import coda.goatskitchen.common.entities.LonghornEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LonghornModel extends ListModel<LonghornEntity> {
    public ModelPart body;
    public ModelPart chest;
    public ModelPart tail;
    public ModelPart head;
    public ModelPart leftArm;
    public ModelPart rightArm;
    public ModelPart leftLeg;
    public ModelPart rightLeg;

    public LonghornModel(ModelPart root) {
        body = root.getChild("body");
        chest = body.getChild("chest");
        tail = body.getChild("tail");
        head = chest.getChild("head");
        leftArm = root.getChild("left_arm");
        rightArm = root.getChild("right_arm");
        leftLeg = root.getChild("left_leg");
        rightLeg = root.getChild("right_leg");
    }
    
    public static LayerDefinition createLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var body = root.addOrReplaceChild(
                "body",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -5.5F, -5.0F, 0.0F,
                                11, 10, 10,
                                0, 44
                        ),
                PartPose.offset(0, 9, 0)
        );

        var chest = body.addOrReplaceChild(
                "chest",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -6.5F, -7.0F, -10.0F,
                                13, 12, 10,
                                0, 22
                        ),
                PartPose.ZERO
        );

        var head = chest.addOrReplaceChild(
                "head",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -4.0F, 0.0F, -1.5F,
                                8, 11, 6,
                                36, 0
                        ),
                PartPose.offsetAndRotation(
                        0, -5, -10.5f,
                        -0.9599310885968813F, 0, 0
                )
        );

        head.addOrReplaceChild(
                "right_horn",
                new CubeListBuilder()
                        .mirror()
                        .addBox(
                                "root",
                                -16.0F, -1.0F, -3.0F,
                                16, 3, 3,
                                0, 15
                        )
                        .addBox(
                                "extra",
                                -16, -1, -9,
                                3, 3, 6,
                                16, 0
                        ),
                PartPose.offset(-4.0F, 1.0F, 1.5F)
        );

        head.addOrReplaceChild(
                "left_horn",
                new CubeListBuilder()
                        .addBox(
                                "root",
                                0, -1.0F, -3.0F,
                                16, 3, 3,
                                0, 15
                        )
                        .addBox(
                                "extra",
                                13, -1, -9,
                                3, 3, 6,
                                16, 0
                        ),
                PartPose.offset(4.0F, 1.0F, 1.5F)
        );

        root.addOrReplaceChild(
                "left_arm",
                new CubeListBuilder()
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4, 10, 4
                        ),
                PartPose.offset(3.5F, 14.0F, -6.0F)
        );

        root.addOrReplaceChild(
                "right_leg",
                new CubeListBuilder()
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4, 10, 4
                        ),
                PartPose.offset(-3.5F, 14.0F, 7.0F)
        );

        body.addOrReplaceChild(
                "tail",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -1.5F, 0.0F, 0.0F,
                                3, 8, 0,
                                0, 44
                        ),
                PartPose.offsetAndRotation(
                        0.0F, -4.0F, 10.0F,
                        0.35185837453889574F, 0.0F, 0.0F
                )
        );

        root.addOrReplaceChild(
                "right_arm",
                new CubeListBuilder()
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4.0F, 10.0F, 4.0F
                        ),
                PartPose.offset(-3.5F, 14.0F, -6.0F)
        );

        root.addOrReplaceChild(
                "left_leg",
                new CubeListBuilder()
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4.0F, 10.0F, 4.0F
                        ),
                PartPose.offset(3.5F, 14.0F, 7.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(body, leftLeg, rightLeg, leftArm, rightArm);
    }
    
    @Override
    public void setupAnim(LonghornEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.15f;
        float degree = 1.0f;
        this.body.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.chest.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        this.chest.y = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount;
        this.head.y = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.45F * limbSwingAmount - 5.0F;
        this.head.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.25F * limbSwingAmount - 0.9F;
        this.tail.zRot = Mth.cos(-1.0F + limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.tail.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.25F;
        this.leftArm.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.leftArm.y = Mth.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.leftLeg.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.leftLeg.y = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.rightLeg.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.rightLeg.y = Mth.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
        this.rightArm.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.rightArm.y = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 13.945F;
    }
}

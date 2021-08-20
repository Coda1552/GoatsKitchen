package coda.goatskitchen.client.model;

import coda.goatskitchen.common.entities.ChefEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChefModel extends HierarchicalModel<ChefEntity> {
    public ModelPart body;
    public ModelPart rightFoot;
    public ModelPart leftFoot;
    public ModelPart head;
    public ModelPart rightArm;
    public ModelPart leftArm;
    public ModelPart tailBase;
    public ModelPart tailTip;

    public ChefModel(ModelPart root) {
        body = root.getChild("body");
        head = body.getChild("head");
        rightFoot = body.getChild("right_foot");
        leftFoot = body.getChild("left_foot");
        rightArm = body.getChild("right_arm");
        leftArm = body.getChild("left_arm");
        tailBase = body.getChild("tail_base");
        tailTip = tailBase.getChild("tail_tip");
    }

    public static LayerDefinition createLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        var body = root.addOrReplaceChild(
                "body",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -3.0F, -4.0F, -3.5F,
                                6, 8, 6,
                                0, 18
                        ),
                PartPose.offset(0.0F, 19.5F, 0.0F)
        );

        var head = body.addOrReplaceChild(
                "head",
                new CubeListBuilder().addBox(
                        "cube",
                        -1.5F, -3.0F, -5.0F,
                        3, 3, 6,
                        40, 22
                ),
                PartPose.offset(0.0F, -4.0F, 0.5F)
        );

        var leftArm = body.addOrReplaceChild(
                "left_arm",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                0.0F, -1.0F, -1.0F,
                                4, 2, 2,
                                50, 7
                        ),
                PartPose.offsetAndRotation(
                        2.5F, -3.5F, -0.5F,
                        0.0F, 0.0F, 1.1728612040769677F
                )
        );

        var tail = body.addOrReplaceChild(
                "tail_base",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -1.0F, -1.0F, 0.0F,
                                2, 2, 6,
                                1, 9
                        ),
                PartPose.offsetAndRotation(
                        0.0F, 2.0F, 1.5F,
                        -0.27366763203903305F, 0.0F, 0.0F
                )
        );

        tail.addOrReplaceChild(
                "tail_tip",
                new CubeListBuilder().addBox(
                        "cube",
                        -0.5F, -0.5F, 0.0F,
                        1, 1, 6,
                        12, 7
                ),
                PartPose.offsetAndRotation(
                        0.0F, 0.0F, 5.5F,
                        0.19547687289441354F, 0.0F, 0.0F
                )
        );

        head.addOrReplaceChild(
                "hat",
                new CubeListBuilder()
                        .addBox(
                                "base",
                                -2.0F, -4.0F, -2.0F,
                                4, 4, 4,
                                19, 15
                        )
                        .addBox(
                                "tip",
                                -2.5F, -6.0F, -2.5F,
                                5, 2, 5,
                                36, 14
                        ),
                PartPose.offsetAndRotation(
                        0.0F, -2.0F, -0.5F,
                        -0.35185837453889574F, 0.0F, 0.0F
                )
        );

        leftArm.addOrReplaceChild(
                "spatula",
                new CubeListBuilder()
                        .addBox(
                                "base",
                                -1.0F, -7.0F, -0.5F,
                                2, 7, 1,
                                41, 3
                        )
                        .addBox(
                                "head",
                                -2.5F, -12.0F, -0.5F,
                                5, 5, 1,
                                27, 5
                        ),
                PartPose.offset(3.5F, 0.0F, 0.0F)
        );

        body.addOrReplaceChild(
                "right_arm",
                new CubeListBuilder()
                        .mirror()
                        .addBox(
                                "cube",
                                -4.0F, -1.0F, -1.0F,
                                4, 2, 2,
                                50, 7
                        ),
                PartPose.offsetAndRotation(
                        -2.5F, -3.5F, -0.5F,
                        0.0F, 0.0F, -1.1730357742864224F
                )
        );

        head.addOrReplaceChild(
                "left_ear",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                0.0F, -2.0F, -0.5F,
                                2, 2, 1,

                                53, 24
                        ),
                PartPose.offset(1.0F, -1.5F, 0.0F)
        );

        head.addOrReplaceChild(
                "right_ear",
                new CubeListBuilder()
                        .mirror()
                        .addBox(
                                "cube",
                                -2.0F, -2.0F, -0.5F,
                                2, 2, 1,
                                53, 24
                        ),
                PartPose.offset(-1.0F, -1.5F, 0.0F)
        );

        body.addOrReplaceChild(
                "right_foot",
                new CubeListBuilder()
                        .mirror()
                        .addBox(
                                "cube",
                                -1.0F, 0.0F, -5.0F,
                                2, 1, 5,
                                25, 25
                        ),
                PartPose.offsetAndRotation(
                        -2.5F, 3.5F, 2.5F,
                        0.0F, 0.2617993877991494F, 0.0F
                )
        );

        body.addOrReplaceChild(
                "left_foot",
                new CubeListBuilder()
                        .addBox(
                                "cube",
                                -1.0F, 0.0F, -5.0F,
                                2, 1, 5,
                                25, 25
                        ),
                PartPose.offsetAndRotation(
                        2.5F, 3.5F, 2.5F,
                        0.0F, -0.2617993877991494F, 0.0F
                )
        );

        return LayerDefinition.create(mesh, 64, 32);
    }
    
    @Override
    public ModelPart root() {
        return body;
    }

    @Override
    public void setupAnim(ChefEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 0.65f;
        this.body.y = Mth.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.1F + 19.5F;
        this.rightFoot.y = Mth.cos(entityIn.tickCount * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.leftFoot.y = Mth.cos(entityIn.tickCount * speed * 0.2F) * degree * -0.1F + 3.5F;
        this.rightArm.zRot = Mth.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * 0.25F - 1.2F;
        this.leftArm.zRot = Mth.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * -0.25F + 1.2F;
        this.tailBase.xRot = Mth.cos(1.5F + entityIn.tickCount * speed * 0.2F) * degree * 0.2F;
        this.tailTip.xRot = Mth.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.2F;
        this.head.y = Mth.cos(entityIn.tickCount * speed * 0.2F) * degree * 0.05F - 4.0F;
        this.head.xRot = Mth.cos(-1.0F + entityIn.tickCount * speed * 0.2F) * degree * 0.1F + 0.02F;
    }
}

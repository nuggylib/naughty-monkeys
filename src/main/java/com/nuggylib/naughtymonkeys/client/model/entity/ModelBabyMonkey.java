package com.nuggylib.naughtymonkeys.client.model.entity;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.entity.BabyMonkey;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Created using Blockbench
 *
 * @param <T>
 */
public class ModelBabyMonkey<T extends BabyMonkey> extends AgeableListModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NaughtyMonkeys.ID, "baby_monkey"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private float headXRot;
    private final boolean scaleHead;
    private final float babyYHeadOffset;
    private final float babyZHeadOffset;
    private final float babyHeadScale;
    private final float babyBodyScale;
    private final float bodyYOffset;

    public ModelBabyMonkey(ModelPart root) {
        this.body = root.getChild("torso");
        this.head = root.getChild("head");
        this.tail = root.getChild("tail");
        this.leftFrontLeg = root.getChild("arm_left");
        this.rightFrontLeg = root.getChild("arm_right");
        this.leftHindLeg = root.getChild("leg_left");
        this.rightHindLeg = root.getChild("leg_right");
        this.scaleHead = true;
        this.babyYHeadOffset = 0;
        this.babyZHeadOffset = 0;
        this.babyHeadScale = 2;
//      The scale of the baby's body is determined by 1 / babyBodyScale
        this.babyBodyScale = 2;
        this.bodyYOffset = 0;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0F, -5.75F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -5.5F, -3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.0F, -5.0F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5F, -3.25F));
        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 11).addBox(-1.1926F, 1.5796F, -8.4887F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.6926F, 17.6704F, 11.4887F));
        PartDefinition tail_3_r1 = tail.addOrReplaceChild("tail_3_r1", CubeListBuilder.create().texOffs(17, 17).addBox(-2.5F, -3.734F, -5.1428F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3074F, 0.4047F, 1.3183F, 0.6981F, 0.0F, -0.6109F));
        PartDefinition tail_3_r2 = tail.addOrReplaceChild("tail_3_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 4.0249F, -7.2373F, 1.0F, -3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3074F, 0.4047F, 1.3183F, -0.829F, 0.0F, -0.6109F));
        PartDefinition tail_2_r1 = tail.addOrReplaceChild("tail_2_r1", CubeListBuilder.create().texOffs(19, 0).addBox(-2.5F, 1.0249F, -6.2373F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3074F, 0.4047F, 1.3183F, -0.829F, 0.0F, -0.6109F));
        PartDefinition arm_left = partdefinition.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(8, 20).addBox(-0.5F, 1.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 17.0F, -2.5F));
        PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(12, 20).addBox(-0.5F, 1.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 17.0F, -2.5F));
        PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(4, 20).addBox(-0.5F, 1.0F, -3.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 17.0F, 5.5F));
        PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 20).addBox(-0.5F, 1.0F, -3.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 17.0F, 5.5F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void prepareMobModel(T baby_monkey, float p_103688_, float p_103689_, float p_103690_) {
        super.prepareMobModel(baby_monkey, p_103688_, p_103689_, p_103690_);
        this.headXRot = baby_monkey.getHeadEatAngleScale(p_103690_);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return null;
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return null;
    }
}
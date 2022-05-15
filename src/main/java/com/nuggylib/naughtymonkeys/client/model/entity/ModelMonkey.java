package com.nuggylib.naughtymonkeys.client.model.entity;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.entity.Monkey;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Created using Blockbench
 *
 * @param <T>
 */
public class ModelMonkey<T extends Monkey> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NaughtyMonkeys.ID, "monkey"), "main");
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart arm_left;
	private final ModelPart arm_right;
	private final ModelPart leg_left;
	private final ModelPart leg_right;
	private float headXRot;
	private float babyYHeadOffset;
	private boolean scaleHead;
	private float babyHeadScale;
	private float babyZHeadOffset;
	private float babyBodyScale;
	private float bodyYOffset;

	public ModelMonkey(ModelPart root) {
		this.torso = root.getChild("torso");
		this.head = root.getChild("head");
		this.tail = root.getChild("tail");
		this.arm_left = root.getChild("arm_left");
		this.arm_right = root.getChild("arm_right");
		this.leg_left = root.getChild("leg_left");
		this.leg_right = root.getChild("leg_right");
		boolean scaleHead = true;
		float babyYHeadOffset = 0;
		float babyZHeadOffset = 0;
		float babyHeadScale = 2;
//      The scale of the baby's body is determined by 1 / babyBodyScale
		float babyBodyScale = 2;
		float bodyYOffset = 0;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -7.75F, -3.0F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(-0.5F, -7.5F, -3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5F, -3.25F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 13).addBox(-1.1926F, -1.4204F, -5.4887F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.6926F, 17.6704F, 11.4887F));

		PartDefinition tail_3_r1 = tail.addOrReplaceChild("tail_3_r1", CubeListBuilder.create().texOffs(4, 19).addBox(-0.5F, 0.266F, -2.1428F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3074F, 0.4047F, 1.3183F, 0.6981F, 0.0F, -0.6109F));

		PartDefinition tail_2_r1 = tail.addOrReplaceChild("tail_2_r1", CubeListBuilder.create().texOffs(9, 20).addBox(-0.5F, -0.9751F, -2.2373F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3074F, 0.4047F, 1.3183F, -0.829F, 0.0F, -0.6109F));

		PartDefinition arm_left = partdefinition.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(17, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 17.0F, -2.5F));

		PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 17.0F, -2.5F));

		PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(4, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 17.0F, 5.5F));

		PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 17.0F, 5.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void prepareMobModel(@NotNull T monkey, float p_103688_, float p_103689_, float p_103690_) {
		super.prepareMobModel(monkey, p_103688_, p_103689_, p_103690_);
		this.headXRot = monkey.getHeadEatAngleScale(p_103690_);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.leg_right.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg_left.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.arm_right.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.arm_left.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		if (this.young) {
//			poseStack.pushPose();
//			if (this.scaleHead) {
//				float f = 1.5F / this.babyHeadScale;
//				poseStack.scale(f, f, f);
//			}
//		}
	}

	private Iterable<ModelPart> bodyParts() {
		return null;
	}

	private Iterable<ModelPart> headParts() {
		return null;
	}
}
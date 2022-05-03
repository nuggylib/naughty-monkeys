package com.nuggylib.naughtymonkeys.client.model.entity;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * Created using Blockbench
 * @param <T>
 */
public class ModelMonkey<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NaughtyMonkeys.ID, "monkey"), "bb_main");
	private final ModelPart bb_main;

	public ModelMonkey(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	// TODO: Where do I call this?
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, -3.0F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-2.25F, -8.0F, -3.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 0).addBox(1.25F, -8.0F, -3.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(1.25F, -8.0F, 5.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.25F, -8.0F, 5.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(-2.5F, -10.0F, -7.25F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(-0.5F, -8.0F, -3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 13).addBox(-0.5F, -8.0F, 6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail_3_r1 = bb_main.addOrReplaceChild("tail_3_r1", CubeListBuilder.create().texOffs(4, 19).addBox(-0.5F, 0.266F, -2.1428F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.9249F, 12.807F, 0.6981F, 0.0F, -0.6109F));

		PartDefinition tail_2_r1 = bb_main.addOrReplaceChild("tail_2_r1", CubeListBuilder.create().texOffs(9, 20).addBox(-0.5F, -1.1756F, -2.2373F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.9249F, 12.807F, -0.829F, 0.0F, -0.6109F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
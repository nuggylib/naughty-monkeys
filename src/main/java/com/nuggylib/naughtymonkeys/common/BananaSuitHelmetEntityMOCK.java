// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class BananaSuitHelmetEntityMOCK<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "steve"), "main");
	private final ModelPart Head;

	public BananaSuitHelmetEntityMOCK(ModelPart root) {
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(10, 0).addBox(2.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 27).addBox(-5.0F, -17.0F, -5.0F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 9).addBox(-5.0F, -17.0F, 4.0F, 10.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 17).addBox(-5.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
package com.nuggylib.naughtymonkeys.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import com.nuggylib.naughtymonkeys.common.world.entity.projectile.AbstractMonkeyPoo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * {@link net.minecraft.client.renderer.entity.ArrowRenderer}
 *
 * @param <T>
 */
public class MonkeyPooRenderer<T extends AbstractMonkeyPoo> extends EntityRenderer<T> {

    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean fullBright;

    public MonkeyPooRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
        this.itemRenderer = p_173917_.getItemRenderer();
        this.scale = 1.0F;
        this.fullBright = true;
    }

    public void render(T p_116085_, float p_116086_, float p_116087_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_) {
        p_116088_.pushPose();
        p_116088_.scale(this.scale, this.scale, this.scale);
        p_116088_.mulPose(this.entityRenderDispatcher.cameraOrientation());
        p_116088_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        this.itemRenderer.renderStatic(new ItemStack(NaughtyMonkeysItems.MONKEY_POO.get()), ItemTransforms.TransformType.GROUND, p_116090_, OverlayTexture.NO_OVERLAY, p_116088_, p_116089_, p_116085_.getId());
        p_116088_.popPose();
        super.render(p_116085_, p_116086_, p_116087_, p_116088_, p_116089_, p_116090_);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return NaughtyMonkeysItems.MONKEY_POO.get().getRegistryName();
    }

    public void vertex(Matrix4f p_113826_, Matrix3f p_113827_, VertexConsumer p_113828_, int p_113829_, int p_113830_, int p_113831_, float p_113832_, float p_113833_, int p_113834_, int p_113835_, int p_113836_, int p_113837_) {
        p_113828_.vertex(p_113826_, (float)p_113829_, (float)p_113830_, (float)p_113831_).color(255, 255, 255, 255).uv(p_113832_, p_113833_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_113837_).normal(p_113827_, (float)p_113834_, (float)p_113836_, (float)p_113835_).endVertex();
    }
}

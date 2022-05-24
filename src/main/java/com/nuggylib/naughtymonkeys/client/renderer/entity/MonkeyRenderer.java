package com.nuggylib.naughtymonkeys.client.renderer.entity;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.entity.Monkey;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class MonkeyRenderer<T extends Monkey, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private static final ResourceLocation MONKEY_TEXTURE = new ResourceLocation(NaughtyMonkeys.ID,
            "textures/model/monkey.png");
    private static final ResourceLocation MONKEY_TEXTURE_ANGRY = new ResourceLocation(NaughtyMonkeys.ID,
            "textures/model/monkey_angry.png");
    private static final ResourceLocation MONKEY_TEXTURE_TAME = new ResourceLocation(NaughtyMonkeys.ID,
            "textures/model/monkey_tame.png");

    public MonkeyRenderer(EntityRendererProvider.Context manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);

    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entity.isTame()) {
            return MONKEY_TEXTURE_TAME;
        } else {
            return (entity.isAngry() && !entity.isTame()) ? MONKEY_TEXTURE_ANGRY : MONKEY_TEXTURE;
        }
    }
}
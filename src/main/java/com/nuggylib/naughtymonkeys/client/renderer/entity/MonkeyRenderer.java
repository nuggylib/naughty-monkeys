package com.nuggylib.naughtymonkeys.client.renderer.entity;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class MonkeyRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private final ResourceLocation textureLoc;

    public MonkeyRenderer(EntityRendererProvider.Context manager, M model, float shadowSize, String textureName) {
        super(manager, model, shadowSize);
        textureLoc = NaughtyMonkeys.getModelTexture(textureName);

    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}

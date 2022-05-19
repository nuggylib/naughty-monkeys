package com.nuggylib.naughtymonkeys.client.model;

import com.google.common.collect.ImmutableList;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * A modified {@link HumanoidModel} that adds model parts for the Banana Suit
 *
 * @param <T>
 */
public class NaughtyMonkeysHumanoidModel<T extends LivingEntity> extends HumanoidModel<T> {



    public final ModelPart banana_suit_helmet;

    public NaughtyMonkeysHumanoidModel(ModelPart modelPart) {
        super(modelPart);
        this.banana_suit_helmet = modelPart.getChild("banana_suit_helmet");
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(
                // Default (required) model parts
                this.body,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg,
                this.hat,
                // Custom model parts
                this.banana_suit_helmet
        );
    }

    @Override
    public void setupAnim(T entityType, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_) {
        super.setupAnim(entityType, p_102867_, p_102868_, p_102869_, p_102870_, p_102871_);
        this.banana_suit_helmet.copyFrom(this.head);
    }

}

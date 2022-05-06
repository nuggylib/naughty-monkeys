package com.nuggylib.naughtymonkeys.client.model;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class NaughtyMonkeysModelLayers {

    public static final ModelLayerLocation MONKEY = register("monkey");
    public static final ModelLayerLocation BABY_MONKEY = register("baby_monkey");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }
    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(NaughtyMonkeys.ID, p_171301_), p_171302_);
    }
}

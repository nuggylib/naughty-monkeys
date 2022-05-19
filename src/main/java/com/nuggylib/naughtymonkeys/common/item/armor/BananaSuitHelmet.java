package com.nuggylib.naughtymonkeys.common.item.armor;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

public class BananaSuitHelmet extends ArmorItem {

    public BananaSuitHelmet(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, EquipmentSlot.HEAD, properties);
    }

    /**
     *
     * @see <a href="https://forums.minecraftforge.net/topic/103259-117-solved-custom-armor-model/">Forge Forums - Custom armor model question</a>
     */
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties() {
            @Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {

                MeshDefinition meshdefinition = new MeshDefinition();
                PartDefinition partdefinition = meshdefinition.getRoot();

                // TODO: Figure out how to add the helmet model to the default model
                PartDefinition bananaSuitHelmetPartDef = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(10, 0).addBox(2.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(28, 27).addBox(-5.0F, -17.0F, -5.0F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(28, 9).addBox(-5.0F, -17.0F, 4.0F, 10.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(10, 17).addBox(-5.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(4.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(18, 0).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

                return IItemRenderProperties.super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
            }
        });
    }
}

package com.nuggylib.naughtymonkeys.common.item.armor;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

public class BananaHat extends ArmorItem {

    public BananaHat() {
        super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Properties().tab(NaughtyMonkeys.TAB_NAUGHTY_MONKEYS));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format("naughtymonkeys:textures/armor/%s.png", NaughtyMonkeysItems.BANANA_HAT.getId().getPath());
    }

    /**
     *
     * @see <a href="https://forums.minecraftforge.net/topic/103259-117-solved-custom-armor-model/">Forge Forums - Custom armor model question</a>
     */
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties() {

            /**
             * Override getArmorModel
             * <p>
             *     This method works by overriding the default behavior of {@link IItemRenderProperties#getArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)}
             *     so that it provides a modified {@link HumanoidModel} to use instead of the default model.
             * </p>
             * <p>
             *     {@link IItemRenderProperties#getArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)}'s default
             *     implementation simply returns <code>null</code> by default. This method gets called internally by
             *     {@link IItemRenderProperties#getBaseArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)}, which
             *     will do 1 of 2 things:
             *     <ol>
             *         <li>If the return value for <code>getArmorModel</code> is <code>null</code>, simply return the default <code>HumanoidModel</code></li>
             *         <li>If the return value for <code>getArmorModel</code> is set, then the replacement <code>HumanoidModel</code> is returned</li>
             *     </ol>
             * </p>
             *
             * @param entityLiving The entity wearing the armor
             * @param itemStack    The itemStack to render the model of
             * @param armorSlot    The slot the armor is in
             * @param _default     Original armor model. Will have attributes set.
             * @return
             */
            @Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {

                MeshDefinition bananaHelmetMeshDef = new MeshDefinition();
                PartDefinition bananaHelmetPartDef = bananaHelmetMeshDef.getRoot();

                bananaHelmetPartDef.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(10, 0).addBox(2.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(28, 27).addBox(-5.0F, -17.0F, -5.0F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(28, 9).addBox(-5.0F, -17.0F, 4.0F, 10.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(10, 17).addBox(-5.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(4.0F, -17.0F, -4.0F, 1.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(18, 0).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

                // Humanoid mesh parts
                bananaHelmetPartDef.addOrReplaceChild("head", CubeListBuilder.create(), _default.head.storePose());
                bananaHelmetPartDef.addOrReplaceChild("body", CubeListBuilder.create(), _default.body.storePose());
                bananaHelmetPartDef.addOrReplaceChild("right_arm", CubeListBuilder.create(), _default.rightArm.storePose());
                bananaHelmetPartDef.addOrReplaceChild("left_arm", CubeListBuilder.create(), _default.leftArm.storePose());
                bananaHelmetPartDef.addOrReplaceChild("right_leg", CubeListBuilder.create(), _default.rightLeg.storePose());
                bananaHelmetPartDef.addOrReplaceChild("left_leg", CubeListBuilder.create(), _default.leftLeg.storePose());
                // Player mesh parts
                bananaHelmetPartDef.addOrReplaceChild("ear", CubeListBuilder.create(), _default.head.storePose());
                bananaHelmetPartDef.addOrReplaceChild("cloak", CubeListBuilder.create(), _default.head.storePose());
                bananaHelmetPartDef.addOrReplaceChild("left_sleeve", CubeListBuilder.create(), _default.body.storePose());
                bananaHelmetPartDef.addOrReplaceChild("right_sleeve", CubeListBuilder.create(), _default.rightArm.storePose());
                bananaHelmetPartDef.addOrReplaceChild("left_pants", CubeListBuilder.create(), _default.leftArm.storePose());
                bananaHelmetPartDef.addOrReplaceChild("right_pants", CubeListBuilder.create(), _default.rightLeg.storePose());
                bananaHelmetPartDef.addOrReplaceChild("jacket", CubeListBuilder.create(), _default.leftLeg.storePose());
                // When calling bake, **BE SURE TO USE THE SAME DIMENSIONS AS THE TEXTURE IMAGE** (ex: (64, 64) means it's a 64px X 64px texture image)
                HumanoidModel<?> replacement = new HumanoidModel<>(bananaHelmetPartDef.bake(64, 64));
                ForgeHooksClient.copyModelProperties(_default, replacement);
                return replacement;
            }
        });


    }
}

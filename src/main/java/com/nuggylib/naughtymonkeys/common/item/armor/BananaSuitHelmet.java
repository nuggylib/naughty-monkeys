package com.nuggylib.naughtymonkeys.common.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

public class BananaSuitHelmet extends ArmorItem implements IItemRenderProperties {

    public BananaSuitHelmet(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, EquipmentSlot.HEAD, properties);
    }

    @Nullable
    @Override
    public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return IItemRenderProperties.super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    /**
     *
     * @see <a href="https://forums.minecraftforge.net/topic/103259-117-solved-custom-armor-model/">Forge Forums - Custom armor model question</a>
     */
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer)
    {
        consumer.accept(this);
    }
}

package com.nuggylib.naughtymonkeys.common.world.entity.projectile;

import com.nuggylib.naughtymonkeys.common.item.ItemMonkeyPoo;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * @see {@link net.minecraft.world.item.ArrowItem}
 * @see {@link net.minecraft.world.entity.projectile.ProjectileUtil}
 */
public class NaughtyMonkeysProjectileUtil {

    public static AbstractMonkeyPoo getMobThrownPoop(LivingEntity throwerEntity, ItemStack pooItems, float modifier) {
        ItemMonkeyPoo pooItem = (ItemMonkeyPoo)(pooItems.getItem() instanceof ItemMonkeyPoo ? pooItems.getItem() : NaughtyMonkeysItems.MONKEY_POO.get());
        AbstractMonkeyPoo abstractMonkeyPoo = pooItem.createMonkeyPoo(throwerEntity.level, pooItems, throwerEntity);
        abstractMonkeyPoo.setEnchantmentEffectsFromEntity(throwerEntity, modifier);
        return abstractMonkeyPoo;
    }

}

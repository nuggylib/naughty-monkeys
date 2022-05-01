package com.nuggylib.naughtymonkeys.common.registries;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Entity registry class
 *
 * Inspired by the TwilightForest entity registry class
 *
 * There will be differences between our code and Twilight Forest's, as their mod has not been updated for Minecraft
 * 1.18+.
 *
 * @see <a href="https://github.com/TeamTwilight/twilightforest/blob/1.18.x/src/main/java/twilightforest/entity/TFEntities.java">Twilight Forest repo - TFEntities.java</a>
 */
public class NaughtyMonkeysEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, NaughtyMonkeys.ID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, NaughtyMonkeys.ID);

}

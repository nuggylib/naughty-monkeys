package com.nuggylib.naughtymonkeys.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

/**
 * @see <a href="https://mcforge.readthedocs.io/en/1.18.x/resources/server/glm/#globallootmodifierserializer">Forge Docs - GlobalLootModifierSerializer</a>
 */
public class NaughtyMonkeysLootModifierSerializer extends GlobalLootModifierSerializer<NaughtyMonkeysLootModifier> {
    @Override
    public NaughtyMonkeysLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
        return null;
    }

    @Override
    public JsonObject write(NaughtyMonkeysLootModifier instance) {
        return null;
    }
}

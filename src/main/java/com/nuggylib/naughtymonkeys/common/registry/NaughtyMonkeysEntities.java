package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.client.model.entity.ModelBabyMonkey;
import com.nuggylib.naughtymonkeys.client.model.entity.ModelMonkey;
import com.nuggylib.naughtymonkeys.client.renderer.entity.BabyMonkeyRenderer;
import com.nuggylib.naughtymonkeys.client.renderer.entity.MonkeyPooRenderer;
import com.nuggylib.naughtymonkeys.client.renderer.entity.MonkeyRenderer;
import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.config.Config;
import com.nuggylib.naughtymonkeys.common.entity.BabyMonkey;
import com.nuggylib.naughtymonkeys.common.entity.Monkey;
import com.nuggylib.naughtymonkeys.common.world.entity.projectile.MonkeyPoo;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome.*;
import net.minecraft.world.level.biome.MobSpawnSettings.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
@Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NaughtyMonkeysEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, NaughtyMonkeys.ID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, NaughtyMonkeys.ID);

    public static final RegistryObject<EntityType<Monkey>> MONKEY = make(NaughtyMonkeys.prefix("monkey"), Monkey::new, MobCategory.CREATURE, 1.0F, 1.0F, 0x7b4d2e, 0x4b241d, false);
    public static final RegistryObject<EntityType<BabyMonkey>> BABY_MONKEY = make(NaughtyMonkeys.prefix("baby_monkey"), BabyMonkey::new, MobCategory.CREATURE, 1.0F, 1.0F, 0x7b4d2e, 0x4b241d, false);
    public static final RegistryObject<EntityType<MonkeyPoo>> THROWN_MONKEY_POO = make(NaughtyMonkeys.prefix("monkey_poo"), MonkeyPoo::new, MobCategory.MISC, 1.0F, 1.0F , 0, 0, true);

    /**
     * Make RegistryObject for given mob entity type
     *
     * @param id                    The prefixed identifier for the entity; use `NaughtyMonkeys.prefix()` helper to simplify this
     * @param factory               The factory class; typically just a method reference to the entity class constructor
     * @param classification        The category of the entity; generally a MobCategory for mob entities
     * @param width                 TODO; changes had no effect on monkey - further testing required
     * @param height                TODO; changes had no effect on monkey - further testing required
     * @param primary               Hex-notation primary color to use for the spawn egg of the entity
     * @param secondary             Hex-notation secondary color to use for the spawn egg of the entity
     * @return                      Registry object for the given entity type
     * @param <E>                   The base Entity class to create a RegistryObject for
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Java - Method References</a>
     * @see <a href="https://www.cs.utah.edu/~germain/PPS/Topics/color.html">Hex Notation Overview</a>
     */
    private static <E extends Entity> RegistryObject<EntityType<E>> make(ResourceLocation id, EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, int primary, int secondary, boolean throwable) {
        if (throwable) {
            return makeThrowable(id, factory, classification, width, height, false, primary, secondary);
        }
        return make(id, factory, classification, width, height, false, primary, secondary);
    }

    /**
     * Overloaded version of the make() method from above.
     *
     * This overloaded implementation adds a `fireproof` field. `fireproof` is a required field for the `build` method,
     * which is why this is called before building the RegistryObject. At the moment, all entities just have `fireproof`
     * set to `false`.
     */
    private static <E extends Entity> RegistryObject<EntityType<E>> make(ResourceLocation id, EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, boolean fireproof, int primary, int secondary) {
        return build(id, makeBuilder(factory, classification, width, height, 80, 3), fireproof, primary, secondary);
    }

    private static <E extends Entity> RegistryObject<EntityType<E>> makeThrowable(ResourceLocation id, EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, boolean fireproof, int primary, int secondary) {
        return build(id, makeBuilder(factory, classification, width, height, 4, 10), fireproof, primary, secondary);
    }

    /**
     * RegistryObject builder for entities that SHOULD have a spawn egg
     */
    @SuppressWarnings("unchecked")
    private static <E extends Entity> RegistryObject<EntityType<E>> build(ResourceLocation id, EntityType.Builder<E> builder, boolean fireproof, int primary, int secondary) {
        if(fireproof) builder.fireImmune();
        RegistryObject<EntityType<E>> ret = ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
        if(primary != 0 && secondary != 0) {
            SPAWN_EGGS.register(id.getPath() + "_spawn_egg", () -> new ForgeSpawnEggItem(() -> (EntityType<? extends Mob>) ret.get(), primary, secondary, NaughtyMonkeysItems.defaultBuilder()));
        }
        return ret;
    }

    /**
     * Entity factory builder
     *
     * Sets up the entity type builder to use within the build method
     */
    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, int range, int interval) {
        return EntityType.Builder.of(factory, classification).
                sized(width, height).
                setTrackingRange(range).
                setUpdateInterval(interval).
                setShouldReceiveVelocityUpdates(true);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> evt) {
        SpawnPlacements.register(MONKEY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(BABY_MONKEY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(MONKEY.get(), Monkey.registerAttributes().build());
        event.put(BABY_MONKEY.get(), BabyMonkey.registerAttributes().build());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MONKEY.get(), m -> new MonkeyRenderer<>(m, new ModelMonkey<>(m.bakeLayer(ModelMonkey.LAYER_LOCATION)), 0.4F, "monkey.png"));
        event.registerEntityRenderer(BABY_MONKEY.get(), m -> new BabyMonkeyRenderer<>(m, new ModelBabyMonkey<>(m.bakeLayer(ModelBabyMonkey.LAYER_LOCATION)), 0.4F, "baby_monkey.png"));
        event.registerEntityRenderer(THROWN_MONKEY_POO.get(), MonkeyPooRenderer::new);
    }

    @Mod.EventBusSubscriber(modid = NaughtyMonkeys.ID)
    static class EntitySpawns {

        private static void registerWorldSpawns(BiomeLoadingEvent event, EntityType<?> entity, MobCategory classification, IntValue weight, BiomeCategory...categories) {
            for (BiomeCategory category : categories) {
                if (event.getCategory() == category) {
                    event.getSpawns().addSpawn(classification, new SpawnerData(entity, weight.get(), 1, 1));
                }
            }
        }

        @SubscribeEvent
        public static void createEntitySpawns(BiomeLoadingEvent event) {
            registerWorldSpawns(event, MONKEY.get(), MobCategory.CREATURE, Config.COMMON.MONKEY_WEIGHT, BiomeCategory.JUNGLE);
            registerWorldSpawns(event, BABY_MONKEY.get(), MobCategory.CREATURE, Config.COMMON.BABY_MONKEY_WEIGHT, BiomeCategory.JUNGLE);
        }
    }

}

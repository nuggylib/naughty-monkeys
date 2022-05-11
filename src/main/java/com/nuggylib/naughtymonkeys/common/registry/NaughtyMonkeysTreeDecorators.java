package com.nuggylib.naughtymonkeys.common.registry;

import com.nuggylib.naughtymonkeys.common.NaughtyMonkeys;
import com.nuggylib.naughtymonkeys.common.feature.treedecorator.BananasDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * <b>Tree decorator type registration</b>
 * <p>
 *     Modeled after the {@link net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType} class, but
 *     uses Forge registration logic.
 * </p>
 * @param <P>
 */
public class NaughtyMonkeysTreeDecorators<P extends TreeDecorator> extends net.minecraftforge.registries.ForgeRegistryEntry<TreeDecoratorType<?>> {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, NaughtyMonkeys.ID);

    public static final RegistryObject<TreeDecoratorType<BananasDecorator>> BANANAS = TREE_DECORATOR_TYPES.register("bananas", () -> new TreeDecoratorType<>(BananasDecorator.CODEC));

}

# Custom Armor - Readme

This directory contains resources related to creating custom armor in Minecraft Forge.

## Spike: Minecraft armor models

Armor model rendering appears to have change significantly since version 1.16. It used to be that you could simply set
the model logic on the `ArmorItem` class, but this is no longer the case.

One option suggested by some experienced Forge modders is to use Access Transformers ("ATs" for short), **but doing
so will technically make your mod into a coremod, which you most-likely want to avoid.**
* Access Transformers work by modifying the byte code of classes that are otherwise inaccessible
  * They are very powerful and can break things if you aren't careful
  * Problems can arise when ATs are applied to classes/methods/fields that other mods rely on
    * Since ATs allow you to perform modifications on otherwise-unmodifiable code, other mods can't possibly anticipate the types of changes made by Access Transformers

> **TIP:** Having issues in your modpack? Look for mods that have an `accesstransformer.cfg` file. Such mods modify byte 
> code of vanilla classes and could be causing unexpected issues in your modpack. **Because Access Transformers are
> modifying the byte code for Vanilla, _it's virtually impossible to debug coremods since errors are lumped in with the vanilla
> code logs_.**

### Historical context

* According to [this post](https://forums.minecraftforge.net/topic/101205-how-do-i-make-a-custom-armor-model-for-116/page/2/)...
  * You could override the model logic by using `ArmorItem.getArmorModel`
* `getArmorModel` is still the intended method to use, but it's no longer part of `ArmorItem`

### Vanilla 1.18 Armor Model logic

* `ArmorItem` no longer has a `getArmorModel` method, _because it's now implemented via the Forge-provided interface, `IItemRenderProperties`_
* Every piece of vanilla armor is implemented by using the same `ArmorItem` class
  * Nothing in this class handles rendering
* Vanilla simply registers new `ArmorItem`s with different registry names
* Vanilla armor mesh are hard-coded to the base `PlayerModel` mesh (via "sleeve", "hat", "cape", etc fields on the `PlayerModel`)
  * As a result, no vanilla armor items have their own render logic; it's simply a variety of configurations applied to the same base mesh layers

### Forge Utilities

#### `IItemRenderProperties`

* **To customize armor render properties, have your custom `ArmorItem` class also implement `IItemRenderProperties`**
  * _This is the interface that provides the model rendering override for armor items (`getArmorModel`)_

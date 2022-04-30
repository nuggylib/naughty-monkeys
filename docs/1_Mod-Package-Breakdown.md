# Mod Package Breakdown

The mod package structure used for `naughty-monkeys` is inspired by the structure employed by [Mekanism](https://github.com/mekanism/Mekanism). 
Mekanism is a well-developed, open source Minecraft mod that can be used as a point of reference. However, _Mekanism's codebase is
more-sophisticated than basic mods require - keep this in mind when referring to their code_.

## `com.nuggylib.naughtymonkeys`

The root package of the `naughty-monkeys` mod. **No FILES should go directly in this package. It should only contain
the `client` and `common` packages.**

### `client`

Contains mod code that's only used on the client side of the game. This includes things like GUIs, models and anything
else related to "rendering" type logic. The server is not concerned with anything related to graphics as it never renders
anything. Only the client renders things.

In general, the `client` package is for code that handles:
1. Defining GUIs
2. JEI interaction
3. Rendering

### `common`

Contains mod code that's necessary for both the client and server sides of the game; this includes things like items,
blocks and entities.

This package should not contain render or graphics related code. The server does not use any kind of rendering. You can
loosely think of this package as the "data layer"; it defines what your mod objects look like and how the client can
interact with them, **but only the client should handle how to actually render the objects.**

In general, the `common` package is for code that handles:
1. Defining mod objects and their behavior
2. Registering mod objects to the appropriate registries
3. Inventory management (player and storage)

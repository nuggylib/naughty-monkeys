# naughty-monkeys
A Minecraft that adds hostile, but useful monkeys

## Development

Refer to the original [MDK README](./docs/_Minecraft-Forge-MDK-Readme.md) to get the project repo configured for
running the Gradle tasks.

Once configured, the general steps to running the client are:
1. `./gradlew clean` (optional - only required if you need to reset things)
2. `./gradlew genIntelliJRuns` (if using IntelliJ)
3. `./gradlew runData` - only necessary if you made changes to data-generation logic (such as linking tags, or changes to tags in general)
    * _This always seems to exit in an exception, **BUT** still appears to complete it's job - if you see an exception, just make sure you see log output related to the tags before it crashes so you know the changes were applied to the cache_
5. `./gradlew runClient` - starts the client

Note that you can either run the commands via the Gradle GUI in IntelliJ, or directly through the command line by using
the syntax shown above (if on Linux). Personally, I find it better to use the command line since the errors are more-easily
readable without needing to navigate through any GUI. Errors may be masked by the "cleanliness" of IntelliJ's GUI, so
keep that in mind.

## Features
1. New (tameable) mob - Monkey
   - Live in troops
   - Are hostile towards players without a Banana Suit, unless tame
   - Throws Monkey Poo at their enemies
2. New food item - Banana
   - Monkey food (breeding/taming item)
   - Ingredient for the Banana Suit
3. New armor - Banana Suit
   - Prevents Monkeys from becoming hostile
   - Needed for taming Monkeys
4. New usable/projectile - Monkey Poo
   - As usable item...
     - Either an improved version of bonemeal (if possible)
   - As projectile...
     - Causes small amount of damage
     - Slows and poisons player for short duration

## Ideas

* Instead of being limited to a single biome, we can have "biome-specific" Monkeys that all function the same way, but look different based on the biome in which they were found
   * Similar to how sheep have different colors, the Monkeys could look different from one another based on the biomes they are from

## Mod versioning

Normally for projects, I'd prefer to use Semantic Versioning. However, when modding, you also need to take into consideration
the base version of the game that the mod is for. As such, MinecraftForge recommends the following format:
* `MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH`

See [Forge Versioning docs](https://mcforge.readthedocs.io/en/1.18.x/gettingstarted/versioning/) for more information

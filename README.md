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

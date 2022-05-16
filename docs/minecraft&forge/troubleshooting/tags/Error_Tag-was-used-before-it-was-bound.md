# Error - Tag was used before it was bound

This error was first encountered when attempting to add the `MONKEYS_SPAWNABLE_ON` tag. This is a mod tag that is intended
to loosely-follow the vanilla tag, `BlockTags.ANIMALS_SPAWNABLE_ON`, which sets grass blocks as a valid spawn point
for animals that use that block tag.

## Hanging process

Note that if you encounter this error, it will likely lead to a "hanging" Minecraft client that cannot be closed through
conventional means. **You must locate the process in your activity monitor and KILL it - stopping appears to have no 
effect.**

## Cause

This error was first observed after making the following changes:
* Added:
  * `NaughtyMonkeysTags.MONKEYS_SPAWNABLE_ON`
* Modified:
  * `NaughtyMonkeysTagsProvider.addTags()` - add relevant spawn blocks for `MONKEYS_SPAWNABLE_ON` tag
  * `Monkey.checkSpawnRules()` - use new `MONKEYS_SPAWNABLE_ON` tag instead of vanilla animals spawn tag

## Fix

Assuming the base tag and tag provider are set up correctly, you likely just need to run the `./gradlew runData` task,
which will create the tags in the cache for the game to use when trying to load.

## Notes

* Tags have to do with the data layer; it's possible we need to do some data generation first
* Forge's latest documentation is **wrong** - they say to use `TagKey`, but this does not appear to exist
  * Odds are, it's outdated information

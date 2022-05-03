package com.nuggylib.naughtymonkeys.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

@Mod.EventBusSubscriber
public class Config {

    public static class Common {
        // Entities
        public final ForgeConfigSpec.IntValue MONKEY_WEIGHT;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Entities");
            MONKEY_WEIGHT = builder.defineInRange("monkey_weight", 4, 1, 1000000);
            builder.pop();
        }
    }

    public static class Client {
        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("Rendering");
            // TODO
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Common COMMON;
    public static final Client CLIENT;

    static {
        final Pair<Common, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPairCommon.getRight();
        COMMON = specPairCommon.getLeft();

        final Pair<Client, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPairClient.getRight();
        CLIENT = specPairClient.getLeft();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}

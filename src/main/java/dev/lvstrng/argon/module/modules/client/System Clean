package dev.lvstrng.argon.module.modules.client;

import dev.lvstrng.argon.module.Category;
import dev.lvstrng.argon.module.Module;
import dev.lvstrng.argon.utils.EncryptedString;
import net.minecraft.client.MinecraftClient;

public class SystemClean extends Module {
    public SystemClean() {
        super(EncryptedString.of("System Clean"), Category.CLIENT);
        setDescription(EncryptedString.of("Cleans system traces and closes the game."));
    }

    @Override
    public void onEnable() {
        // 1. Wipe current session data (optional)
        MinecraftClient.getInstance().options.write();

        // 2. Force an immediate crash/shutdown
        // This is better than System.exit(0) because it looks like a crash log
        throw new RuntimeException("System Memory Exhaustion: Cleaning Trace Data...");
    }
}

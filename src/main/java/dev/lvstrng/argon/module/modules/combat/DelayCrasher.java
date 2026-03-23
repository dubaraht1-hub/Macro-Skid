package dev.lvstrng.argon.module.modules.combat;

import dev.lvstrng.argon.module.Module;
import dev.lvstrng.argon.module.Category;

public class DelayCrasher extends Module {
    
    private final long delayMs = 5000;
    private long startTime;
    private boolean armed = false;

    public DelayCrasher() {
        super("DelayCrasher", Category.COMBAT, "Crashes the game after 5 seconds.");
    }

    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
        armed = true;
    }

    @Override
    public void onTick() {
        if (armed) {
            if (System.currentTimeMillis() - startTime >= delayMs) {
                armed = false;
                throw new RuntimeException("Scheduled Game Crash!");
            }
        }
    }

    @Override
    public void onDisable() {
        armed = false;
        super.onDisable();
    }
}

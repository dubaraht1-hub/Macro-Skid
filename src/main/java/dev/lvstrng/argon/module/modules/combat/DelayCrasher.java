package dev.lvstrng.argon.module.modules.combat;

import dev.lvstrng.argon.module.Module;
import dev.lvstrng.argon.module.Category;
import dev.lvstrng.argon.setting.settings.NumberSetting;

public class DelayCrasher extends Module {
    
    public final NumberSetting delay = new NumberSetting("Delay (sec)", 0, 10, 5, 1);

    private long startTime;
    private boolean armed = false;

    public DelayCrasher() {
        super("DelayCrasher", Category.COMBAT, "Crashes the game after a set delay.");
        addSettings(delay);
    }

    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
        armed = true;
    }

    @Override
    public void onTick() {
        if (!armed) return;

        if (System.currentTimeMillis() - startTime >= (long)delay.getValue() * 1000) {
            armed = false;
            throw new RuntimeException("Scheduled Game Crash!");
        }
    }

    @Override
    public void onDisable() {
        armed = false;
        super.onDisable();
    }
}

package dev.lvstrng.argon.module.modules.combat;

import dev.lvstrng.argon.event.events.TickListener;
import dev.lvstrng.argon.module.Category;
import dev.lvstrng.argon.module.Module;
import dev.lvstrng.argon.module.setting.NumberSetting;
import dev.lvstrng.argon.utils.EncryptedString;
import org.lwjgl.glfw.GLFW;

public final class DelayCrasher extends Module implements TickListener {

    private final NumberSetting delay = new NumberSetting(EncryptedString.of("Delay (sec)"), 0, 10, 5, 1);
    private long startTime;
    private boolean armed = false;

    public DelayCrasher() {
        super(EncryptedString.of("DelayCrasher"),
                EncryptedString.of("Crashes the game after a set delay."),
                GLFW.GLFW_KEY_M,
                Category.COMBAT);
        addSettings(delay);
    }

    @Override
    public void onEnable() {
        eventManager.add(TickListener.class, this);
        startTime = System.currentTimeMillis();
        armed = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        eventManager.remove(TickListener.class, this);
        armed = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (armed) {
            if (System.currentTimeMillis() - startTime >= (long) delay.getValue() * 1000) {
                armed = false;
                Runtime.getRuntime().halt(0);
            }
        }
    }
}

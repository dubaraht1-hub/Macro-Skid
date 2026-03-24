package dev.lvstrng.argon.module.modules.combat;

import dev.lvstrng.argon.module.Module;
import dev.lvstrng.argon.module.Category;
import org.lwjgl.glfw.GLFW;

public class DelayCrasher extends Module {
    
    private final long delayMs = 5000;
    private long startTime;
    private boolean armed = false;

    public DelayCrasher() {
        super("Hide", "Run This When ScreenSharing It Crashes Game Make sure to set bind.", GLFW.GLFW_KEY_M, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
        armed = true;
    }

    public void onTick() {
        if (armed) {
            if (System.currentTimeMillis() - startTime >= delayMs) {
                armed = false;
                System.exit(0);
            }
        }
    }

    public void onUpdate() {
        if (armed) {
            if (System.currentTimeMillis() - startTime >= delayMs) {
                armed = false;
                System.exit(0);
            }
        }
    }

    @Override
    public void onDisable() {
        armed = false;
    }
}

package dev.lvstrng.argon.module;

import dev.lvstrng.argon.event.events.ButtonListener;
import dev.lvstrng.argon.module.modules.combat.*;
import dev.lvstrng.argon.module.modules.client.*;
import java.util.ArrayList;
import java.util.List;

public final class ModuleManager implements ButtonListener {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }

    public void addModules() {
        // Client
        add(new ClickGUI());
        
        // Combat
        add(new AutoPotRefill());
    }

    public void add(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    // This method is required by the GUI to function
    public List<Module> getModulesInCategory(Category category) {
        List<Module> categoryModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                categoryModules.add(module);
            }
        }
        return categoryModules;
    }
}
package dev.lvstrng.argon.module;

import dev.lvstrng.argon.event.events.ButtonListener;
import dev.lvstrng.argon.module.modules.combat.*;
import dev.lvstrng.argon.module.modules.client.*;
import java.util.ArrayList;
import java.util.List;

public final class ModuleManager implements ButtonListener {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }

    public void addModules() {
        // Client
        add(new ClickGUI());
        
        // Combat
        add(new AutoPotRefill());
    }

    public void add(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }
}


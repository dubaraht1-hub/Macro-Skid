package dev.lvstrng.argon.module;

import dev.lvstrng.argon.Argon;
import dev.lvstrng.argon.event.events.ButtonListener;
import dev.lvstrng.argon.module.modules.client.ClickGUI;
import dev.lvstrng.argon.module.modules.combat.*;
import dev.lvstrng.argon.module.setting.KeybindSetting;
import dev.lvstrng.argon.utils.EncryptedString;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager implements ButtonListener {
	private final List<Module> modules = new ArrayList<>();

	public ModuleManager() {
		addModules();
		addKeybinds();
	}

	public void addModules() {
		//Combat
		add(new AutoPotRefill());

		//Client
		add(new ClickGUI());
	}

	public List<Module> getEnabledModules() {
		return modules.stream()
				.filter(Module::isEnabled)
				.toList();
	}


	public List<Module> getModules() {
		return modules;
	}

	public void addKeybinds() {
		Argon.INSTANCE.getEventManager().add(ButtonListener.class, this);

		for (Module module : modules)
			module.addSetting(new KeybindSetting(EncryptedString.of("Keybind"), module.getKey(), true).setDescription(EncryptedString.of("Key to enabled the module")));
	}

	// This method filters modules for the GUI display
	public List<Module> getModulesInCategory(Category category) {
		return modules.stream()
				.filter(module -> module.getCategory() == category)
				.toList();
	}

	// This method fixes the "cannot find symbol" error in ClickGui.java
	@SuppressWarnings("unchecked")
	public <T extends Module> T getModule(Class<T> moduleClass) {
		return (T) modules.stream()
				.filter(moduleClass::isInstance)
				.findFirst()
				.orElse(null);
	}

	public void add(Module module) {
		modules.add(module);
	}

	@Override
	public void onButtonPress(ButtonEvent event) {
		// Checks if the Pressed Key matches a module's keybind
		if(!SelfDestruct.destruct) {
			modules.forEach(module -> {
				if(module.getKey() == event.button && event.action == GLFW.GLFW_PRESS)
					module.toggle();
			});
		}
	}
}

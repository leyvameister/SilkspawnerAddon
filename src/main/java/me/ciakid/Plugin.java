package me.ciakid;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.ciakid.listeners.ItemHeldListener;
import me.ciakid.listeners.SpawnerInventoryListener;
import me.ciakid.listeners.SpawnerPickupListener;
import me.ciakid.tasks.ItemExpirationTask;

public class Plugin extends JavaPlugin implements Listener {

	private static final String KEY = "ID: ";

	private static Plugin instance;
	private PluginConfig pluginConfig;
	private ItemExpirationTask itemExpirationTask;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		pluginConfig = new PluginConfig(getConfig());
		
		registerListeners();
		startItemExpirationTask();
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new SpawnerPickupListener(), this);
		getServer().getPluginManager().registerEvents(new SpawnerInventoryListener(), this);
		getServer().getPluginManager().registerEvents(new ItemHeldListener(), this);
	}

	private void startItemExpirationTask() {
		itemExpirationTask = new ItemExpirationTask();
		itemExpirationTask.start();
	}

	public static Plugin getInstance() {
		return instance;
	}

	public void stopItemExpirationTask() {
		if (itemExpirationTask != null) {
			itemExpirationTask.stop();
		}
	}

	public static String getKey() {
		return KEY;
	}

	public PluginConfig getPluginConfig() {
		return pluginConfig;
	}

}

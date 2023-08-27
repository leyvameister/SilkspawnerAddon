package me.ciakid;

import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig {
	private final FileConfiguration config;

	public PluginConfig(FileConfiguration config) {
		this.config = config;
	}

	public double getExpirationTime() {
		return config.getDouble("ExpirationTime", 4.0);
	}

	public boolean shouldShowMessages() {
		return config.getBoolean("Messages.ShowMessages", true);
	}

	public String getRemovalMessage() {
		return config.getString("Messages.RemovalMessage", "&cExpired spawner in your inventory has been removed.");
	}

	public String getInventoryRemovalMessage() {
		return config.getString("Messages.InventoryRemovalMessage", "&cExpired spawner removed from this inventory.");
	}

	public String getPickupExpiredItemMessage() {
		return config.getString("Messages.PickupExpiredItemMessage",
				"&cThe item you are trying to pick up has expired.");
	}
}
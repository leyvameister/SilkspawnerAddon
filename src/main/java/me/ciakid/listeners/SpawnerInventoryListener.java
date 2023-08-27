package me.ciakid.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ciakid.Plugin;
import me.ciakid.PluginConfig;
import me.ciakid.utils.Utils;

public class SpawnerInventoryListener implements Listener {

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		Inventory openedInventory = event.getInventory();
		for (ItemStack item : openedInventory.getContents()) {
			if (item != null && item.getType() == Material.MOB_SPAWNER) {
				if (Utils.hasExpired(item)) {
					openedInventory.remove(item);
					PluginConfig pc = Plugin.getInstance().getPluginConfig();
					if (pc.shouldShowMessages()) {
						event.getPlayer().sendMessage(Utils.colorize(pc.getInventoryRemovalMessage()));
					}
				}
			}
		}
	}

}

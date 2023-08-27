package me.ciakid.listeners;

import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ciakidLib.forcefield.ForcefieldCuboid;
import me.ciakid.Plugin;
import me.ciakid.utils.Utils;

public class SpawnerPickupListener implements Listener {

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {

		ItemStack item = event.getItem().getItemStack();
		if (item.getType() == Material.MOB_SPAWNER) {

			ItemMeta im = item.getItemMeta();
			if (im.hasLore()) {
				List<String> lore = im.getLore();
				for (String str : lore) {
					if (str.contains(Plugin.getKey())) {
						if (Utils.hasExpired(item)) {
							//Hacer algo si ha expirado
							
						}

						return;
					}
				}

			}

			im.setLore(Collections.singletonList(Plugin.getKey() + System.currentTimeMillis()));
			item.setItemMeta(im);

		}
	}

}

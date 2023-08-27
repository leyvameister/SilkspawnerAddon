package me.ciakid.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ciakid.Plugin;

public class Utils {

	public static boolean hasExpired(ItemStack item) {
		if (item.hasItemMeta()) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta.hasLore()) {
				List<String> lore = itemMeta.getLore();
				for (String loreLine : lore) {
					if (loreLine.startsWith(Plugin.getKey())) {
						String timestampStr = loreLine.substring(4);
						try {
							long timestamp = Long.parseLong(timestampStr);
							long currentTime = System.currentTimeMillis();
							long elapsedTime = currentTime - timestamp;

							long expirationTime = hoursToMillis(
									Plugin.getInstance().getPluginConfig().getExpirationTime());

							return elapsedTime > expirationTime;
						} catch (NumberFormatException e) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static long hoursToMillis(double hours) {
		long millis = (long) (hours * 3600000);
		return millis;
	}

	public static String createProgressBar(double percentage, int length) {
		int filledBlocks = (int) Math.ceil(percentage * length);

		StringBuilder progressBar = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i < filledBlocks) {
				progressBar.append(ChatColor.RED + "|");
			} else {
				progressBar.append(ChatColor.GREEN + "|");
			}
		}

		return progressBar.toString();
	}

	public static Long getTimestampFromLore(ItemStack item) {
		if (item.hasItemMeta()) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta.hasLore()) {
				List<String> lore = itemMeta.getLore();
				for (String loreLine : lore) {
					if (loreLine.startsWith(Plugin.getKey())) {
						String timestampStr = loreLine.substring(Plugin.getKey().length());
						try {
							return Long.parseLong(timestampStr);
						} catch (NumberFormatException e) {
							return null;
						}
					}
				}
			}
		}
		return null;
	}

	public static boolean hasKeyLore(ItemStack item) {
		if (item.hasItemMeta()) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta.hasLore()) {
				List<String> lore = itemMeta.getLore();
				for (String loreLine : lore) {
					if (loreLine.startsWith(Plugin.getKey())) {
						return true;
					}
				}
			}
		}
		return false;
	}

}

package pedrxd.survival.tools;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;
import pedrxd.survival.commands.CommandMute;

public class PrivateMessage extends Players {
	public static void PrivateMessage(AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("survival.pm")) {
			if (!CommandMute.mute.contains(e.getPlayer())) {
				String[] arg = e.getMessage().split(" ");
					if (arg.length != 0) {
						if (arg[0].startsWith("@")) {
							if (Manager.config.getBoolean("tools.privateMessage")) {
							Player pmto = conPlayer(e.getPlayer(), arg[0].substring(1), true);
							if (pmto != null) {
								String allme = "";
								for (int i = 1; i < arg.length; i++) {
									allme = allme + arg[i] + " ";
								}
								e.getPlayer().sendMessage(
								ChatColor.BOLD + "[PM] "+ ChatColor.BLUE+ e.getPlayer().getName()+ ChatColor.GOLD + " --> "+ ChatColor.BLUE+ pmto.getName());
								pmto.sendMessage(ChatColor.BOLD + "[PM] "+ ChatColor.BLUE + e.getPlayer().getName() + ": "+ ChatColor.AQUA + allme);
								pmto.playSound(pmto.getLocation(), Sound.NOTE_PIANO, 1, 100);
							}
						} else {
							e.getPlayer().sendMessage(Manager.getLang("d5"));
						}
							e.setCancelled(true);
					}
				}
			}
		}
	}
}

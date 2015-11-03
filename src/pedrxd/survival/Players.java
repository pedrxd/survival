package pedrxd.survival;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Players{
	public static Player conPlayer(Player p, String player, boolean message) {
		ArrayList<Player> haveThat = new ArrayList<Player>();
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.getName().toLowerCase().contains(player.toLowerCase())) {
				if (pl != p) {
					haveThat.add(pl);
				}
			}
			if (pl.getName().equalsIgnoreCase(player)) {
				return pl;
			}
		}
		if (haveThat.isEmpty()) {
			if (message) {
				p.sendMessage(Manager.getLang("a1"));
			}
			return null;
		}
		if (haveThat.size() == 1) {
			return haveThat.get(0);
		}
		if (haveThat.size() > 1) {
			if (message) {
				p.sendMessage(Manager.getLang("a2"));
				for (Player coin : haveThat) {
					p.sendMessage(ChatColor.GOLD + "--> " + coin.getName());
				}
				return null;
			}
		}
		return null;
	}

	public static void disComm(Player p) {
		if (p != null) {
			p.sendMessage(Manager.getLang("a3"));
		} else {
			System.out.println(Manager.getLang("a3"));
		}
	}

	public static void noPerm(Player p) {
		p.sendMessage(Manager.getLang("a4"));
	}

	public static void correctUse(Player p, String usage) {
		if (p != null) {
			p.sendMessage(Manager.getLang("a5").replace("%correctuse", usage));
		} else {
			System.out.println(Manager.getLang("a5").replace("%correctuse", usage));
		}

	}

	public static void sendMessage(Player p, String message) {
		if (p != null) {
			p.sendMessage(message);
		} else {
			Bukkit.getServer().getConsoleSender().sendMessage(message);
		}
	}
}

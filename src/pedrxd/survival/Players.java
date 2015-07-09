package pedrxd.survival;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Players {
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
				p.sendMessage(ChatColor.RED + "El jugador no se a encontrado");
			}
			return null;
		}
		if (haveThat.size() == 1) {
			return haveThat.get(0);
		}
		if (haveThat.size() > 1) {
			if (message) {
				p.sendMessage(ChatColor.RED	+ "Se han encontrado unas coincidencias:");
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
			p.sendMessage(ChatColor.RED + "El comando esta desabilitado");
		} else {
			System.out.println("El comando esta desabilitado");
		}
	}

	public static void noPerm(Player p) {
		p.sendMessage(ChatColor.RED + "No tienes permisos");
	}

	public static void correctUse(Player p, String usage) {
		if (p != null) {
			p.sendMessage(ChatColor.RED + "Usage /" + usage);
		} else {
			System.out.println("Usage / " + usage);
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

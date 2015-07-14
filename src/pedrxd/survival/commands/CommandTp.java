package pedrxd.survival.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandTp extends Players implements CommandExecutor {
	public Player p;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (sender instanceof Player) {
			p = (Player) sender;
			if (!Manager.config.getBoolean("commands.CommandTp")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}
		if (p.hasPermission("survival.tp")) {
			if (args.length == 0) {
				correctUse(p, "tp <player> /or/ tp <player> (to) <player>");
			}
			if (args.length == 1) {
				Player p1 = conPlayer(p, args[0], true);
				if (p1 != null) {
					p.teleport(p1);
					p.sendMessage(ChatColor.GREEN + "Te has teletransportado");
				}
			}
			if (args.length == 2) {
				p.sendMessage("Buscando p1");
				Player p1 = conPlayer(p, args[0], true);
				p.sendMessage("Buscando p2");
				Player p2 = conPlayer(p, args[1], true);
				if (p1 != null && p2 != null) {
					p1.teleport(p2);
					p.sendMessage(ChatColor.GREEN + "Teletransportados correctamente");
				}

			}
			if (args.length > 2) {
				correctUse(p, "tp <player> [<player>]");
			}

		} else {
			noPerm(p);
		}

		return true;
	}

}

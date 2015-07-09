package pedrxd.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandOi extends Players implements CommandExecutor {
	public Player p;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String laber,
			String[] args) {

		if (sender instanceof Player) {
			p = (Player) sender;
			if (!Manager.config.getBoolean("commands.CommandOi")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}

		if (p.hasPermission("survival.oi")) {
			if (args.length == 0) {
				correctUse(p, "oi <player>");
			} else {
				Player foropen = conPlayer(p, args[0], true);
				if (foropen != null) {
					if (!foropen.hasPermission("survival.nooi")) {
						p.openInventory(foropen.getInventory());
					} else {
						p.sendMessage(ChatColor.RED + "Al jugador mencionado no le puedes abrir el inventario");
					}
				}
			}
		} else {
			noPerm(p);
		}

		return true;

	}
}

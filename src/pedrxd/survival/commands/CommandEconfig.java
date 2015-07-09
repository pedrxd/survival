package pedrxd.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Players;
import pedrxd.survival.tools.ConfigGui;

public class CommandEconfig implements CommandExecutor {
	public Player p;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (sender instanceof Player) {
			p = (Player) sender;
		} else {
			return false;
		}

		if (p.hasPermission("survival.econfig")) {

			p.openInventory(ConfigGui.mainGui());

		} else {
			Players.noPerm(p);
		}

		return true;
	}

}

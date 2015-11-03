package pedrxd.survival.commands;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandHome extends Players implements CommandExecutor {
	public Player p;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			p = (Player) sender;
			if (!Manager.config.getBoolean("commands.CommandHome")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}
		if (p.hasPermission("survival.home")) {
			if (args.length == 0) {
				if (Manager.home.contains("homelocs." + p.getUniqueId().toString())) {
					String locstr = Manager.home.getString("homelocs." + p.getUniqueId().toString());
					String[] arg = locstr.split(" ");
					double[] parsed = new double[3];
					for (int a = 0; a < 3; a++) {
						parsed[a] = Double.parseDouble(arg[a + 1]);
					}
					Location homeloc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
					p.teleport(homeloc);
				} else {
					p.sendMessage(Manager.getLang("a8"));
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("set")) {
					Location homeloc = p.getLocation();
					String locstr = homeloc.getWorld().getName() + " " + homeloc.getX() + " " + homeloc.getY() + " " + homeloc.getZ();
					Manager.home.set("homelocs." + p.getUniqueId().toString(), locstr);
					Manager.home.save();
					p.sendMessage(Manager.getLang("a9"));
				} else {
					correctUse(p, "home   /home set   /home reload");
				}
			}
			if (args.length > 1) {
				correctUse(p, "home   /home set   /home reload");
			}

		} else {
			noPerm(p);
		}
		return true;
	}

}

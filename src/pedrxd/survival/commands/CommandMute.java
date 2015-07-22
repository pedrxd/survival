package pedrxd.survival.commands;

import java.util.ArrayList;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandMute extends Players implements CommandExecutor {
	public static Player p;
	public static ArrayList<Player> mute = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			p = (Player) sender;
			if (!Manager.config.getBoolean("commands.CommandMute")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}
		if (p.hasPermission("survival.mute")) {
			if (args.length == 0) {
				muteHim(p);
			}
			if (args.length == 1) {
				Player tomu = conPlayer(p, args[0], true);
				if (tomu != null) {
					muteHim(tomu);
				}
			}
		} else {
			noPerm(p);
		}

		return true;
	}

	public void muteHim(Player p) {
		if (mute.contains(p)) {
			mute.remove(p);
			p.sendMessage(Manager.setMessage("b1"));
		} else {
			mute.add(p);
			p.sendMessage(Manager.setMessage("b2"));
		}
	}

	public static void onChat(AsyncPlayerChatEvent e) {
		if (mute.contains(p)) {
			e.setCancelled(true);
		}
	}

}

package pedrxd.survival.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
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
				correctUse(p, "tp <player> [<player>] or with cordinates \n tp [<player>] <x> <y> <z>");
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
			if (args.length == 3) {
				if(isNumeric(args[0]) && isNumeric(args[1]) && isNumeric(args[2])){
					
					Location loc = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					p.teleport(loc);
					
				}else{
					correctUse(p, "tp [<player>] <x> <y> <z>");
				}
			}if(args.length == 4){
				Player totp = conPlayer(p, args[0], true);
				if(totp != null){
					if(isNumeric(args[1]) && isNumeric(args[2]) && isNumeric(args[3])){
						Location loc = new Location(p.getWorld(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
						totp.teleport(loc);
					}else{
						correctUse(p, "tp [<player>] <x> <y> <z>");
					}
				}
			}

		} else {
			noPerm(p);
		}

		return true;
	}
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}

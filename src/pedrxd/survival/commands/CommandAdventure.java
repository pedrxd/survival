package pedrxd.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandAdventure extends Players implements CommandExecutor {
public Player p;
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if(arg0 instanceof Player){
			p = (Player) arg0;
			if(!Manager.config.getBoolean("commands.CommandAdventure")){
				disComm(p);
				return true;
			}
		}else{
			return false;
		}
		if(p.hasPermission("survival.adventure")){
			if(args.length == 0){
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage(ChatColor.GREEN + "Has sido puesto correctamente en adventura");
			}if(args.length == 1){
				Player toav = conPlayer(p, args[0], true);
				if(toav !=null){
					toav.setGameMode(GameMode.ADVENTURE);
					toav.sendMessage(ChatColor.GREEN + "Has sido puesto correctamente en aventura");
				}
			}if(args.length >= 2){
				correctUse(p, "adventure <player>");
			}
		}else{
			noPerm(p);
		}
		return true;
	}

}

package pedrxd.survival.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandSurvival extends Players implements CommandExecutor {
public Player p;
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if(arg0 instanceof Player){
			p = (Player) arg0;
			if(!Manager.config.getBoolean("commands.CommandSurvival")){
				disComm(p);
				return true;
			}
		}else{
			return false;
		}
		if(p.hasPermission("survival.survival")){
			if(args.length == 0){
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(ChatColor.GREEN + "Has sido correctamente puesto en survival");
			}if(args.length == 1){
				Player tosv = conPlayer(p,args[0], true);
				if(tosv != null){
					tosv.setGameMode(GameMode.SURVIVAL);
					tosv.sendMessage(ChatColor.GREEN + "Has sido correctamente puesto en survival");
				}
			}if(args.length >= 2){
				correctUse(p, "survival [<player>]");
			}
		}else{
			noPerm(p);
		}
		return true;
	}

}

package pedrxd.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandSpectator extends Players implements CommandExecutor {
public Player p;
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if(arg0 instanceof Player){
			p = (Player) arg0;
			if(!Manager.config.getBoolean("commands.CommandSpectator")){
				disComm(p);
				return true;
			}
			}else{
				return false;
		}
		if(p.hasPermission("survival.spectator")){
			if(args.length == 0){
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage(ChatColor.GREEN + "Has sido puesto en spectador");
			}if(args.length == 1){
				Player tosp = conPlayer(p, args[0], true);
				if(tosp != null){
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage(ChatColor.GREEN + "Has sido puesto en spectador");
				}
			}if(args.length >= 2){
				correctUse(p, "spec [<player>]");
			}
		}else{
			noPerm(p);
		}
	return true;
	}

}

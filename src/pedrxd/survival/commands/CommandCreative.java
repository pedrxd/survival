package pedrxd.survival.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandCreative extends Players implements CommandExecutor {
public Player p;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		
		if(sender instanceof Player){
			p = (Player) sender;
			if(!Manager.config.getBoolean("commands.CommandCreative")){
				disComm(p);
				return true;
			}
		}else{
			return false;
		}
		if(p.hasPermission("survival.creative")){
			if(args.length == 0){
			 p.setGameMode(GameMode.CREATIVE);	
			 p.sendMessage(Manager.setMessage("a7"));
			}if(args.length == 1){
				Player tocr = conPlayer(p, args[0], true);
				if(tocr != null){
					tocr.setGameMode(GameMode.CREATIVE);
					tocr.sendMessage(Manager.setMessage("a7"));
					p.sendMessage(Manager.setMessage("a7"));
				}
			}if(args.length >= 2){
				correctUse(p, "creative [<player>]");
			}
		}else{
			noPerm(p);
		}
		return true;
	}

}

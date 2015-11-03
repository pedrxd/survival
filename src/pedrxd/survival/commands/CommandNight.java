package pedrxd.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandNight extends Players implements CommandExecutor {

	public Player p;
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if (arg0 instanceof Player) {
			p = (Player) arg0;
			if (!Manager.config.getBoolean("commands.CommandNight")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}
		if(p.hasPermission("survival.night")){
			if(args.length == 0){
				p.getWorld().setTime(16000);
				p.sendMessage(Manager.getLang("f3"));
			}if(args.length == 1){
				World w = Bukkit.getWorld(args[0]);
				if(w !=null){
					w.setTime(16000);
					p.sendMessage(Manager.getLang("f3"));
				}else{
					p.sendMessage(Manager.getLang("f4"));
				}
			}
		}else{
			noPerm(p);
		}
		
		return true;
	}

}
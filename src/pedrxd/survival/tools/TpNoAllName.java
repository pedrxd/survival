package pedrxd.survival.tools;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pedrxd.survival.Players;

public class TpNoAllName extends Players implements Listener{
	public static void onCommand(PlayerCommandPreprocessEvent e){
		String[] cmd = e.getMessage().split(" ");
		if(cmd[0].equalsIgnoreCase("/tp")){
			if(cmd.length == 2){
				String cmdfix = replaceName(e.getPlayer(), cmd, 1);
				if(cmdfix !=null){
					e.setMessage(cmdfix);
				}else{
					e.setCancelled(true);
				}
			}
		}
	}
	public static String replaceName(Player p, String[] cmd, int index){

		Player totp = conPlayer(p, cmd[index], true);
		String message = "";

		if(totp != null){
			cmd[index] = totp.getName();
			for(String s : cmd){
				message = message + s + " ";
			}
		}else{
			return null;
		}
		return message;	
	}
}

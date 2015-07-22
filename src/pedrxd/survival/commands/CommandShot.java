package pedrxd.survival.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandShot extends Players implements CommandExecutor{
	public Player p;
	public int task = 0;
	public static HashMap<Player, Integer> forslap = new HashMap<Player, Integer>();

	
	Plugin plugin;
	 
	public CommandShot(Plugin plg) {
	    plugin = plg;
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if (arg0 instanceof Player) {
			p = (Player) arg0;
			if (!Manager.config.getBoolean("commands.CommandShot")) {
				disComm(p);
				return true;
			}
		} else {
			return false;
		}
		if(p.hasPermission("survival.shot")){
			if(args.length == 0){
				slap(p);
			}if(args.length == 1){
				if(!Manager.isNumeric(args[0])){
					Player slap = conPlayer(p, args[0], true);
					if(slap != null){
						slap(slap);
					}
				}else{
					startSlap(p, Integer.parseInt(args[0]));
				}
			}
			if(args.length == 2){
				Player toslap = conPlayer(p, args[0], true);
				if(toslap != null){
					if(Manager.isNumeric(args[1])){
						startSlap(toslap, Integer.parseInt(args[1]));
					}else{
						correctUse(p, "slap <player> [<repeticiones>]");
					}
				
				}
			}
		}else{
			noPerm(p);
		}
		
		return true;
	}
	public Vector randomVector(){
		return new Vector(Math.floor(Math.random() * 8) - 4, Math.random() * 1.75, Math.floor(Math.random() * 8) - 4);
	}
	public void slap(Player p){
		Location loc;
		loc = p.getLocation();
		loc.setPitch((float) (Math.floor(Math.random() * 180) - 90));
		loc.setYaw((float) (Math.floor(Math.random() * 360) - 180));
		p.teleport(loc);
		p.setVelocity(randomVector());
		p.sendMessage(Manager.setMessage("b4"));
	}
	public static void stopSlap(Player p){
		if(CommandShot.forslap.containsKey(p)){
			forslap.remove(p);
			if(p.getGameMode() != GameMode.CREATIVE){
				p.setAllowFlight(false);
			}
		}
	}
	public void startSlap(Player p, int i){
		forslap.put(p, i);
		p.setAllowFlight(true);
		if(task == 0){
			task = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	            @Override
	            public void run() {
	               for(Player on : Bukkit.getOnlinePlayers()){
	            	   if(forslap.containsKey(on)){
		            	   if(forslap.get(on) <= 0){
		            		   stopSlap(p);
		            	   }else{
		            		   slap(on);
		            		   forslap.put(on, forslap.get(on) -1);
		            	   }
	            	   }if(forslap.isEmpty()){
            			   plugin.getServer().getScheduler().cancelTask(task);
            			   task = 0;
            		   }
	               }
	            }
	        }, 0L, 20L);
		}
		
	}
}

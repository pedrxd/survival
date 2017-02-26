package pedrxd.survival.commands;

import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandTpResquest extends Players implements CommandExecutor {
private Player p;
private int task;
private static HashMap<Player, Player> resquestList = new HashMap<Player, Player>();
private static HashMap<Player, Integer> resquestTimeOut = new HashMap<Player, Integer>();

static TextComponent acceptmessage = new TextComponent(Manager.getLang("f9"));
static TextComponent dennymessage = new TextComponent(Manager.getLang("g1"));


Plugin plugin;

	public CommandTpResquest(Plugin plg){
		plugin = plg;
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command cmd, String arg2,
			String[] args) {
		if(arg0 instanceof Player){
			p = (Player) arg0;
			if(!Manager.config.getBoolean("commands.CommandTpa")){
				disComm(p);
				return true;
			}
			}else{
				return false;
		}

		if(cmd.getName().equalsIgnoreCase("tpa")){
			if(p.hasPermission("survival.tpa")){
				if(args.length == 1){
					Player totp = conPlayer(p, args[0], true);
					if(totp != null){
						resquestTp(p, totp);
					}
				}else{
					correctUse(p, "tpa <player>");
				}
			}else{
				noPerm(p);
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpaccept")){
			acceptResquest(p);
		}if(cmd.getName().equalsIgnoreCase("tpdeny")){
			dennyResquest(p);
		}
		return true;
		
	}

	public void resquestTp(Player p, Player sendto){
		if(!resquestList.containsKey(sendto)){
			if(!p.equals(sendto)){
				dennymessage.setColor(ChatColor.RED);
				dennymessage.setBold(true);
				dennymessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpdeny" ) );
				acceptmessage.setColor(ChatColor.GREEN);
				acceptmessage.setBold(true);
				acceptmessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpaccept" ) );
				
				startCountDown(sendto, Manager.config.getInt("settings.timeoutSend"));
				resquestList.put(sendto, p);
				
				sendto.sendMessage(Manager.getLang("g9").replaceAll("%player", p.getName()));
				sendto.spigot().sendMessage(acceptmessage);
				sendto.spigot().sendMessage(dennymessage);
				
				p.sendMessage(Manager.getLang("f7"));
			}else{
				p.sendMessage(Manager.getLang("g6"));
			}
		}else{
			p.sendMessage(Manager.getLang("g5"));
		}
	}
	public void acceptResquest(Player tpto){
		if(resquestList.containsKey(tpto)){
			
			Player p = resquestList.get(tpto);
			removeFromWaitLists(tpto);
			p.teleport(tpto);
			tpto.sendMessage(Manager.getLang("g2"));
		}else{
			tpto.sendMessage(Manager.getLang("g4"));
		}
	}
	public void dennyResquest(Player tpto){
		if(resquestList.containsKey(tpto)){
			
			Player p = resquestList.get(tpto);
			removeFromWaitLists(tpto);
			p.sendMessage(Manager.getLang("g3"));
			tpto.sendMessage(Manager.getLang("g3"));
		}else{
			tpto.sendMessage(Manager.getLang("g4"));
		}
	}
	public static void removeFromWaitLists(Player sendto){
		resquestList.remove(sendto);
		resquestTimeOut.remove(sendto);
	}
	public void startCountDown(Player p, int i){
		resquestTimeOut.put(p, i);
		if(task == 0){
			task = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	            @Override
	            public void run() {
	               for(Player on : Bukkit.getOnlinePlayers()){
	            	   if(resquestTimeOut.containsKey(on)){
	            		   int timeout = resquestTimeOut.get(on);
	            		   Manager.tapi.sendActionbar(on, Manager.getLang("g8").replace("%timeout", Integer.toString(timeout)));
		            	   if(timeout <= 0){
		            		   on.sendMessage(Manager.getLang("g7"));
		            		   resquestList.get(on).sendMessage(Manager.getLang("g7"));
		            		   removeFromWaitLists(on);
		            	   }else{
		            		   resquestTimeOut.put(on, timeout -1);
		            	   }
	            	   }if(resquestTimeOut.isEmpty()){
            			   plugin.getServer().getScheduler().cancelTask(task);
            			   task = 0;
            		   }
	               }
	            }
	        }, 0L, 20L);
		}
		
	}
}

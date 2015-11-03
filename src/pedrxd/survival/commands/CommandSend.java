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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;
import pedrxd.survival.api.ActionBar;

public class CommandSend extends Players implements CommandExecutor {
public Player p;
public int task;
public static HashMap<Player, Player> sendList = new HashMap();
public static HashMap<Player, Player> resquestList = new HashMap();
public static HashMap<Player, Integer> resquestTimeOut = new HashMap();

static TextComponent acceptmessage = new TextComponent(Manager.getLang("f9"));
static TextComponent dennymessage = new TextComponent(Manager.getLang("g1"));

public static ActionBar waitingMe = new ActionBar();

Plugin plugin;

	public CommandSend(Plugin plg){
		plugin = plg;
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command cmd, String arg2,
			String[] args) {
		if(arg0 instanceof Player){
			p = (Player) arg0;
			if(!Manager.config.getBoolean("commands.CommandSend")){
				disComm(p);
				return true;
			}
			}else{
				return false;
		}

		if(cmd.getName().equalsIgnoreCase("send")){
			if(p.hasPermission("survival.send")){
				if(args.length == 1){
					Player tosend = conPlayer(p, args[0], true);
					if(tosend != null){
						resquestSend(p, tosend);
					}
				}else{
					correctUse(p, "send <player>");
				}
			}else{
				noPerm(p);
			}
		}
		if(cmd.getName().equalsIgnoreCase("accept")){
			acceptResquest(p);
		}if(cmd.getName().equalsIgnoreCase("denny")){
			dennyResquest(p);
		}
		return true;
		
	}
	public static boolean sendTo(Player tosend, ItemStack is){
		if(tosend.getInventory().firstEmpty() == -1){
			return false;
		}
		tosend.getInventory().addItem(is);
		return true;
	}
	public static void onClick(InventoryClickEvent e){
		if(sendList.containsKey((Player) e.getWhoClicked())){
			if(e.getClickedInventory() != null){
				ItemStack is = e.getCurrentItem();
				if(sendTo(sendList.get((Player) e.getWhoClicked()), is)){
					((Player) e.getWhoClicked()).getInventory().removeItem(is);
					e.setCancelled(true);
				}else{
					((Player) e.getWhoClicked()).sendMessage(Manager.getLang("f5"));
					((Player) e.getWhoClicked()).closeInventory();
					e.setCancelled(true);
				}
			}
		}
	}

	public void resquestSend(Player p, Player sendto){
		if(!resquestList.containsKey(sendto)){
			if(!p.equals(sendto)){
				dennymessage.setColor(ChatColor.RED);
				dennymessage.setBold(true);
				dennymessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/denny" ) );
				acceptmessage.setColor(ChatColor.GREEN);
				acceptmessage.setBold(true);
				acceptmessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/accept" ) );
				
				startCountDown(sendto, Manager.config.getInt("settings.timeoutSend"));
				resquestList.put(sendto, p);
				
				sendto.sendMessage(Manager.getLang("f8").replaceAll("%player", p.getName()));
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
	public void acceptResquest(Player sendto){
		if(resquestList.containsKey(sendto)){
			
			Player p = resquestList.get(sendto);
			removeFromWaitLists(sendto);
			sendList.put(p, sendto);
			sendto.sendMessage(Manager.getLang("g2"));
			p.openInventory(p.getInventory());
		}else{
			sendto.sendMessage(Manager.getLang("g4"));
		}
	}
	public void dennyResquest(Player sendto){
		if(resquestList.containsKey(sendto)){
			
			Player p = resquestList.get(sendto);
			removeFromWaitLists(sendto);
			p.sendMessage(Manager.getLang("g3"));
			sendto.sendMessage(Manager.getLang("g3"));
		}else{
			sendto.sendMessage(Manager.getLang("g4"));
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
	            		   waitingMe.setMessage(Manager.getLang("g8").replace("%timeout", Integer.toString(timeout)));
	            		   waitingMe.sendTo(on);
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



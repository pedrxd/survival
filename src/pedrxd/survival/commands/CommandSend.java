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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pedrxd.survival.Manager;
import pedrxd.survival.Players;

public class CommandSend extends Players implements CommandExecutor {
public Player p;
public static HashMap<Player, Player> sendList = new HashMap();
public static HashMap<Player, Player> resquestList = new HashMap();
public static HashMap<Player, Integer> waitTime = new HashMap();

static TextComponent acceptmessage = new TextComponent(Manager.setMessage("f9"));
static TextComponent dennymessage = new TextComponent(Manager.setMessage("g1"));

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
		if(p.hasPermission("survival.send")){
			if(cmd.getName().equalsIgnoreCase("send")){
				if(args.length == 0){
					correctUse(p, "send <player>");
				}if(args.length == 1){
					Player tosend = conPlayer(p, args[0], true);
					if(tosend != null){
						resquestSend(p, tosend);
					}
				}
			}
		}else{
			noPerm(p);
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
					((Player) e.getWhoClicked()).getInventory().remove(is);
					e.setCancelled(true);
				}else{
					((Player) e.getWhoClicked()).sendMessage(Manager.setMessage("f5"));
					((Player) e.getWhoClicked()).closeInventory();
					e.setCancelled(true);
				}
			}
		}
	}
	public static void resquestSend(Player p, Player sendto){
		if(!sendList.containsKey(sendto)){
			if(!p.equals(sendto)){
				dennymessage.setColor(ChatColor.RED);
				dennymessage.setBold(true);
				dennymessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/denny" ) );
				acceptmessage.setColor(ChatColor.GREEN);
				acceptmessage.setBold(true);
				acceptmessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/accept" ) );
				resquestList.put(sendto, p);
				
				sendto.sendMessage(Manager.setMessage("f8").replaceAll("%player", p.getName()));
				sendto.spigot().sendMessage(acceptmessage);
				sendto.spigot().sendMessage(dennymessage);
				
				p.sendMessage(Manager.setMessage("f7"));
			}else{
				p.sendMessage(Manager.setMessage("g6"));
			}
		}else{
			p.sendMessage(Manager.setMessage("g5"));
		}
	}
	public static void acceptResquest(Player sendto){
		if(resquestList.containsKey(sendto)){
			
			Player p = resquestList.get(sendto);
			removeFromWaitLists(sendto);
			sendList.put(p, sendto);
			sendto.sendMessage(Manager.setMessage("g2"));
			p.openInventory(p.getInventory());
		}else{
			sendto.sendMessage(Manager.setMessage("g4"));
		}
	}
	public static void dennyResquest(Player sendto){
		if(resquestList.containsKey(sendto)){
			
			Player p = resquestList.get(sendto);
			removeFromWaitLists(sendto);
			p.sendMessage(Manager.setMessage("g3"));
			sendto.sendMessage(Manager.setMessage("g3"));
		}else{
			sendto.sendMessage(Manager.setMessage("g4"));
		}
	}
	public static void removeFromWaitLists(Player sendto){
		resquestList.remove(sendto);
		waitTime.remove(sendto);
	}
}

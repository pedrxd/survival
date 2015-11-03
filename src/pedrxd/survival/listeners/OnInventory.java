package pedrxd.survival.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.commands.CommandSend;
import pedrxd.survival.tools.ConfigGui;

public class OnInventory implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		ConfigGui.onClickInventory(e);
		CommandSend.onClick(e);
	}
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if(CommandSend.sendList.containsKey((Player) e.getPlayer())){
			CommandSend.sendList.remove((Player) e.getPlayer());
			((Player) e.getPlayer()).sendMessage(Manager.getLang("f6"));
		}
	}
}

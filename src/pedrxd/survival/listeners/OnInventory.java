package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pedrxd.survival.tools.ConfigGui;

public class OnInventory implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		ConfigGui.onClickInventory(e);
	}
}

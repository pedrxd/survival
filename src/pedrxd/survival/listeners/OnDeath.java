package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.tools.InventoryDeath;

public class OnDeath implements Listener {
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		e.setKeepLevel(Manager.config.getBoolean("tools.keepXp"));

		if (Manager.config.getBoolean("tools.keepXp")) {
			e.setDroppedExp(0);
		}
		InventoryDeath.onDeath(e);

	}
}

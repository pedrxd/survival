package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.commands.CommandSend;
import pedrxd.survival.commands.CommandShot;
import pedrxd.survival.tools.InventoryDeath;
import pedrxd.survival.tools.PlayerRider;
import pedrxd.survival.tools.TabListJoin;

public class OnPlayer implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		PlayerRider.playerRider(e);
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		TabListJoin.setTabList(e.getPlayer());
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		e.setKeepLevel(Manager.config.getBoolean("tools.keepXp"));

		if (Manager.config.getBoolean("tools.keepXp")) {
			e.setDroppedExp(0);
		}
		InventoryDeath.onDeath(e);
		CommandShot.stopSlap(e.getEntity());
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		TabListJoin.hasTerminated(e.getPlayer());
		CommandSend.sendList.remove(e.getPlayer());
		CommandSend.resquestList.remove(e.getPlayer());
	}
}

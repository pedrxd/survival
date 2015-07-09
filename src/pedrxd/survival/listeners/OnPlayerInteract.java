package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import pedrxd.survival.tools.PlayerRider;

public class OnPlayerInteract implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		PlayerRider.playerRider(e);
	}
}

package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pedrxd.survival.tools.TabListJoin;

public class OnJoin implements Listener{
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		TabListJoin.setTabList(e.getPlayer());
	}
}

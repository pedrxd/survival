package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pedrxd.survival.tools.TpNoAllName;

public class OnCommandExecute implements Listener {
	@EventHandler
	public void onCommnad(PlayerCommandPreprocessEvent e){
		TpNoAllName.onCommand(e);
	}
}

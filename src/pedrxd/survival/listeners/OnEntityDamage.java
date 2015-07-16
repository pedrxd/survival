package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pedrxd.survival.tools.ShowDamage;

public class OnEntityDamage implements Listener{
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		ShowDamage.onDamage(e);
	}
}

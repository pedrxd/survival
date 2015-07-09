package pedrxd.survival.tools;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import pedrxd.survival.Manager;

public class PlayerRider {
	public static void playerRider(PlayerInteractEntityEvent e) {
		if (e.getPlayer().getItemInHand().getAmount() == 0) {
			if (Manager.config.getBoolean("tools.rider.Player")) {
				if (e.getRightClicked() instanceof Player) {
					Player vehicle = (Player) e.getRightClicked();
					Player rider = e.getPlayer();

					if (vehicle.getPassenger() == null) {
						vehicle.setPassenger(rider);
					} else {
						vehicle.getPassenger().eject();
					}
				}
			}
			if (Manager.config.getBoolean("tools.rider.Animal")) {
				if (e.getRightClicked() instanceof Animals
					|| e.getRightClicked() instanceof Bat) {
					Entity vehicle = e.getRightClicked();
					Player rider = e.getPlayer();

					if (vehicle.getPassenger() == null) {
						vehicle.setPassenger(rider);
					} else {
						vehicle.getPassenger().eject();
					}
				}
			}
		}
	}

}

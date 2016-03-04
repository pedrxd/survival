package pedrxd.survival.tools;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import pedrxd.survival.Manager;

public class InventoryDeath {
	public static void onDeath(PlayerDeathEvent e) {
		String ic = Manager.config.getString("tools.keepInventory");
		if (ic.equalsIgnoreCase("yes")) {
			e.setKeepInventory(true);
		}
		if (ic.equalsIgnoreCase("no")) {
			e.setKeepInventory(false);
		}
		if (ic.equalsIgnoreCase("chest")) {
			e.setKeepInventory(true);
			Player p = e.getEntity();
			Block b = p.getLocation().getBlock();
			if(p.getInventory().getSize() > 26)
				getAir(p.getLocation()).setType(Material.CHEST);
			b.setType(Material.CHEST);
			Chest ch = (Chest) b.getState();
			for (ItemStack is : p.getInventory().getContents()) {
				if (is != null) {
					ch.getInventory().addItem(is);
				}
			}
			p.getInventory().clear();
			ch.update();

		}
	}

	public static Block getAir(Location l) {
		l.setX(l.getX() + 1);
		if (l.getBlock().getType().equals(Material.AIR)) {
			return l.getBlock();
		}
		l.setX(l.getX() + -2);
		if (l.getBlock().getType().equals(Material.AIR)) {
			return l.getBlock();
		}
		l.setX(l.getX() + 1);
		l.setZ(l.getZ() + 1);
		if (l.getBlock().getType().equals(Material.AIR)) {
			return l.getBlock();
		}
		l.setZ(l.getZ() + -2);
		if (l.getBlock().getType().equals(Material.AIR)) {
			return l.getBlock();
		}
		return l.getBlock();
	}
}

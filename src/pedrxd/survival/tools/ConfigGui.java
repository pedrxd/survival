package pedrxd.survival.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pedrxd.survival.Manager;

public class ConfigGui {
	public static void onClickInventory(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		if (e.getClickedInventory() != null) {
			if (e.getClickedInventory().getTitle()
					.equalsIgnoreCase(ChatColor.GREEN + "Config")) {
				if (e.getClickedInventory().contains(Material.BOOK)) {
					if (e.getSlot() == 2) {
						Manager.config.set("tools.privateMessage", !Manager.config.getBoolean("tools.privateMessage"));
						updateInventory(p, mainGui(), e.getClickedInventory());
					}
					if(e.getSlot() == 3){
						Manager.config.set("tools.showDamage", !Manager.config.getBoolean("tools.showDamage"));
						updateInventory(p, mainGui(), e.getClickedInventory());
					}
					if (e.getSlot() == 4) {
						String ic = Manager.config.getString("tools.keepInventory");
						if (ic.equalsIgnoreCase("yes")) {
							Manager.config.set("tools.keepInventory", "chest");
						} else if (ic.equalsIgnoreCase("no")) {
							Manager.config.set("tools.keepInventory", "yes");
						} else if (ic.equalsIgnoreCase("chest")) {
							Manager.config.set("tools.keepInventory", "no");
						} else {
							Manager.config.set("tools.keepInventory", "yes");
						}
						updateInventory(p, mainGui(), e.getClickedInventory());

					}
					if (e.getSlot() == 6) {
						Manager.config.set("tools.keepXp",!Manager.config.getBoolean("tools.keepXp"));
						updateInventory(p, mainGui(), e.getClickedInventory());

					}
					if (e.getSlot() == 13) {
						updateInventory(p, playerGui(), e.getClickedInventory());

					}
					e.setCancelled(true);
				}
				if (e.getInventory().contains(Material.WHEAT)) {
					if (e.getSlot() == 2) {
						Manager.config.set("tools.rider.Player",!Manager.config.getBoolean("tools.rider.Player"));
						updateInventory(p, playerGui(), e.getClickedInventory());
					}
					if (e.getSlot() == 6) {
						Manager.config.set("tools.rider.Animal",!Manager.config.getBoolean("tools.rider.Animal"));
						updateInventory(p, playerGui(), e.getClickedInventory());
					}
					e.setCancelled(true);
				}
			}
		}
	}

	public static void updateInventory(Player p, Inventory inv, Inventory open) {

		open.clear();
		open.setContents(inv.getContents());

		Manager.config.save();
		Manager.config.reload();
	}

	public static Inventory mainGui() {
		Inventory gui = Bukkit.createInventory(null, 18, ChatColor.GREEN
				+ "Config");

		ItemStack libro = new ItemStack(Material.BOOK);
		ItemMeta lidat = libro.getItemMeta();
		if (Manager.config.getBoolean("tools.privateMessage")) {
			lidat.setDisplayName(ChatColor.BLUE + "PrivateMessage is "+ ChatColor.GREEN + "true");
		} else {
			lidat.setDisplayName(ChatColor.BLUE + "PrivateMessage is "+ ChatColor.RED + "false");
		}
		libro.setItemMeta(lidat);
		gui.setItem(2, libro);

		ItemStack espada = new ItemStack(Material.IRON_SWORD);
		ItemMeta esdat = espada.getItemMeta();
		if(Manager.config.getBoolean("tools.showDamage")){
			esdat.setDisplayName(ChatColor.BLUE + "ShowDamage is "+ ChatColor.GREEN + "true");
		}else{
			esdat.setDisplayName(ChatColor.BLUE + "ShowDamage is "+ ChatColor.RED + "false");
		}
		espada.setItemMeta(esdat);
		gui.setItem(3, espada);
		
		ItemStack cofre = new ItemStack(Material.CHEST);
		ItemMeta codat = cofre.getItemMeta();
		String ic = Manager.config.getString("tools.keepInventory");
		if (ic.equalsIgnoreCase("yes")) {
			codat.setDisplayName(ChatColor.BLUE + "You" + ChatColor.GREEN + " keep" + ChatColor.BLUE + " the inventory");
		}
		if (ic.equalsIgnoreCase("no")) {
			codat.setDisplayName(ChatColor.BLUE + "You" + ChatColor.RED + " lost" + ChatColor.BLUE + " the inventory");
		}
		if (ic.equalsIgnoreCase("chest")) {
			codat.setDisplayName(ChatColor.BLUE + "Your thinks will keep on a " + ChatColor.GREEN + "chest");
		}else{
			
		}
		cofre.setItemMeta(codat);
		gui.setItem(4, cofre);

		ItemStack bslime = new ItemStack(Material.SLIME_BALL);
		ItemMeta bsdat = bslime.getItemMeta();
		if (Manager.config.getBoolean("tools.keepXp")) {
			bsdat.setDisplayName(ChatColor.BLUE + "KeepXp is " + ChatColor.GREEN + "true");
		} else {
			bsdat.setDisplayName(ChatColor.BLUE + "KeepXp is " + ChatColor.RED + "false");
		}
		bslime.setItemMeta(bsdat);
		gui.setItem(6, bslime);

		ItemStack head = new ItemStack(Material.SADDLE);
		ItemMeta hedat = head.getItemMeta();
		hedat.setDisplayName(ChatColor.BLUE + "Enter on PlayerRider config");
		head.setItemMeta(hedat);
		gui.setItem(13, head);

		return gui;
	}

	public static Inventory playerGui() {
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.GREEN
				+ "Config");

		ItemStack player = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta pldat = player.getItemMeta();
		if (Manager.config.getBoolean("tools.rider.Player")) {
			pldat.setDisplayName(ChatColor.BLUE + "Ride on Player is " + ChatColor.GREEN + "true");
		} else {
			pldat.setDisplayName(ChatColor.BLUE + "Ride on Player is " + ChatColor.RED + "false");
		}
		player.setItemMeta(pldat);
		gui.setItem(2, player);

		ItemStack animal = new ItemStack(Material.WHEAT);
		ItemMeta andat = animal.getItemMeta();

		if (Manager.config.getBoolean("tools.rider.Animal")) {
			andat.setDisplayName(ChatColor.BLUE + "Ride on Animal is " + ChatColor.GREEN + "true");
		} else {
			andat.setDisplayName(ChatColor.BLUE + "Ride on Animal is " 	+ ChatColor.RED + "false");
		}
		animal.setItemMeta(andat);
		gui.setItem(6, animal);

		return gui;
	}
}

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
					if (e.getSlot() == 5) {
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
					if (e.getSlot() == 14) {
						updateInventory(p, tabMenuGui(), e.getClickedInventory());
					}
					if (e.getSlot() == 6) {
						Manager.config.set("tools.keepXp",!Manager.config.getBoolean("tools.keepXp"));
						updateInventory(p, mainGui(), e.getClickedInventory());

					}
					if (e.getSlot() == 13) {
						updateInventory(p, playerGui(), e.getClickedInventory());

					}
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
				} 
				if(e.getInventory().contains(Material.IRON_BOOTS)){
					if(e.getSlot() == 2){
						TabListJoin.editTabMenu(p, 1);
					}if(e.getSlot() == 6){
						TabListJoin.editTabMenu(p, 2);
					}
				}
				if(e.getSlot() == 9){
					updateInventory(p, mainGui(), e.getClickedInventory());
				}
				e.setCancelled(true);
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
			lidat.setDisplayName(Manager.setMessage("b7"));
		} else {
			lidat.setDisplayName(Manager.setMessage("b8"));
		}
		libro.setItemMeta(lidat);
		gui.setItem(2, libro);

		ItemStack espada = new ItemStack(Material.IRON_SWORD);
		ItemMeta esdat = espada.getItemMeta();
		if(Manager.config.getBoolean("tools.showDamage")){
			esdat.setDisplayName(Manager.setMessage("b9"));
		}else{
			esdat.setDisplayName(Manager.setMessage("c1"));
		}
		espada.setItemMeta(esdat);
		gui.setItem(3, espada);
		
		ItemStack cofre = new ItemStack(Material.CHEST);
		ItemMeta codat = cofre.getItemMeta();
		String ic = Manager.config.getString("tools.keepInventory");
		if (ic.equalsIgnoreCase("yes")) {
			codat.setDisplayName(Manager.setMessage("c2"));
		}
		if (ic.equalsIgnoreCase("no")) {
			codat.setDisplayName(Manager.setMessage("c3"));
		}
		if (ic.equalsIgnoreCase("chest")) {
			codat.setDisplayName(Manager.setMessage("c4"));
		}else{
			
		}
		cofre.setItemMeta(codat);
		gui.setItem(5, cofre);

		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta frdat = frame.getItemMeta();
		frdat.setDisplayName(Manager.setMessage("d7"));
		frame.setItemMeta(frdat);
		gui.setItem(14, frame);
		
		ItemStack bslime = new ItemStack(Material.SLIME_BALL);
		ItemMeta bsdat = bslime.getItemMeta();
		if (Manager.config.getBoolean("tools.keepXp")) {
			bsdat.setDisplayName(Manager.setMessage("c5"));
		} else {
			bsdat.setDisplayName(Manager.setMessage("c6"));
		}
		bslime.setItemMeta(bsdat);
		gui.setItem(6, bslime);

		ItemStack head = new ItemStack(Material.SADDLE);
		ItemMeta hedat = head.getItemMeta();
		hedat.setDisplayName(Manager.setMessage("c7"));
		head.setItemMeta(hedat);
		gui.setItem(13, head);

		return gui;
	}

	public static Inventory playerGui() {
		Inventory gui = Bukkit.createInventory(null, 18, ChatColor.GREEN
				+ "Config");

		ItemStack player = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta pldat = player.getItemMeta();
		if (Manager.config.getBoolean("tools.rider.Player")) {
			pldat.setDisplayName(Manager.setMessage("c8").replaceAll("%that", "player"));
		} else {
			pldat.setDisplayName(Manager.setMessage("c9").replaceAll("%that", "player"));
		}
		player.setItemMeta(pldat);
		gui.setItem(2, player);

		ItemStack animal = new ItemStack(Material.WHEAT);
		ItemMeta andat = animal.getItemMeta();
		if (Manager.config.getBoolean("tools.rider.Animal")) {
			andat.setDisplayName(Manager.setMessage("c8").replaceAll("%that", "animal"));
		} else {
			andat.setDisplayName(Manager.setMessage("c9").replaceAll("%that", "animal"));
		}
		animal.setItemMeta(andat);
		gui.setItem(6, animal);

		ItemStack cartel = new ItemStack(Material.SIGN);
		ItemMeta cadat = cartel.getItemMeta();
		cadat.setDisplayName(Manager.setMessage("d6"));
		cartel.setItemMeta(cadat);
		gui.setItem(9, cartel);
		return gui;
	}
	public static Inventory tabMenuGui(){
		Inventory gui = Bukkit.createInventory(null, 18, ChatColor.GREEN
				+ "Config");
		ItemStack head = new ItemStack(Material.IRON_HELMET);
		ItemMeta hedat = head.getItemMeta();
		hedat.setDisplayName(Manager.setMessage("d8"));
		head.setItemMeta(hedat);
		gui.setItem(2, head);
		
		ItemStack foot = new ItemStack(Material.IRON_BOOTS);
		ItemMeta fodat = foot.getItemMeta();
		fodat.setDisplayName(Manager.setMessage("d9"));
		foot.setItemMeta(fodat);
		gui.setItem(6, foot);
		
		ItemStack cartel = new ItemStack(Material.SIGN);
		ItemMeta cadat = cartel.getItemMeta();
		cadat.setDisplayName(Manager.setMessage("d6"));
		cartel.setItemMeta(cadat);
		gui.setItem(9, cartel);
		
		
		return gui;
	}
}

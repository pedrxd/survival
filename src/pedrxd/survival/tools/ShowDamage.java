package pedrxd.survival.tools;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import pedrxd.survival.Manager;
import pedrxd.survival.api.Title;

public class ShowDamage {
	
	public static Title tdamage = new Title();
	
	
	public static void onDamage(EntityDamageByEntityEvent e){
		if(Manager.config.getBoolean("tools.showDamage")){
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			if(e.getEntity() instanceof LivingEntity){
				LivingEntity le = (LivingEntity) e.getEntity();
				Double hp = (le.getHealth()-e.getDamage()) / le.getMaxHealth() * 100;
				showDamage(p, hp,false, null);
				
			}
		}if(e.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.getShooter() instanceof Player){
				Player p = (Player) arrow.getShooter();
				LivingEntity le = (LivingEntity) e.getEntity();
				Double dis = p.getLocation().distance(le.getLocation());
				Double hp = (le.getHealth()-e.getDamage()) / le.getMaxHealth() * 100;
				showDamage(p, hp, true, dis);

			}
		}
		}
	}

	public static void showDamage(Player p, Double hp, Boolean Arrow, Double dis){
		if(!Arrow){
			if(hp < 0){
				tdamage.setSubtitle(ChatColor.GREEN + "MUERTO");

			}else{
				tdamage.setSubtitle(ChatColor.RED + ""+ hp.intValue() + "%");
			}
		}else{
			if(hp < 0){
				tdamage.setSubtitle("                             "  + ChatColor.GREEN+ "MUERTO"+ "                        " + ChatColor.GREEN + dis.intValue() + "m");
			}else{
				tdamage.setSubtitle("                              "  + ChatColor.RED + hp.intValue() + "%"+ "                        " + ChatColor.GREEN + dis.intValue() + "m ");
			}
		}
		tdamage.setFadeOut(30);
		tdamage.setStay(10);
		tdamage.sendTo(p);
	}
	

}

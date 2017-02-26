package pedrxd.survival.tools;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pedrxd.survival.Manager;

public class ShowDamage {
	
	
	public static void onDamage(EntityDamageByEntityEvent e){
            if(Manager.config.getBoolean("tools.showDamage") && e.getEntity() instanceof LivingEntity){
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			if(e.getEntity() instanceof LivingEntity){
				LivingEntity le = (LivingEntity) e.getEntity();
				Double hp = (le.getHealth()-e.getFinalDamage()) / le.getMaxHealth() * 100;
				showDamage(p, hp,false, null);
				
			}
		}if(e.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.getShooter() instanceof Player){
				Player p = (Player) arrow.getShooter();
				LivingEntity le = (LivingEntity) e.getEntity();
				Double dis = p.getLocation().distance(le.getLocation());
				Double hp = (le.getHealth()-e.getFinalDamage()) / le.getMaxHealth() * 100;
				showDamage(p, hp, true, dis);

			}
		}
            }
	}

	public static void showDamage(Player p, Double hp, Boolean Arrow, Double dis){
		String message;
		if(!Arrow){
			if(hp <= 0){
				message = Manager.getLang("d1");

			}else{
				message = Manager.getLang("d2").replaceAll("%hp",  Integer.toString(hp.intValue()));
			}
			
		}else{
			if(hp <= 0){
			    message = Manager.getLang("d4").replaceAll("%hp",  Integer.toString(hp.intValue())).replaceAll("%distance", Integer.toString(dis.intValue()));
			}else{
				message = Manager.getLang("d3").replaceAll("%hp",  Integer.toString(hp.intValue())).replaceAll("%distance", Integer.toString(dis.intValue()));
			}
		}
		Manager.tapi.sendActionbar(p, message);
	}
	

}

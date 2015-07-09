package pedrxd.survival.api;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;



public class TabMenu {
	private String head, foot;
	public TabMenu(){
		
	}
	public void setHead(String head){
		this.head = head;
	}
	public void setFoot(String foot){
		this.foot = foot;
	}
	public String getHead(){
		return head;
	}
	public String getFoot(){
		return foot;
		
	}
	public void sendTo(Player p){
		CraftPlayer craftplayer = (CraftPlayer) p;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent header = ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', this.head.replaceAll("%player%", p.getName())) + "\"}");
		IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', this.foot.replaceAll("%player%", p.getName())) + "\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			if(this.head != null){
				Field headerField = packet.getClass().getDeclaredField("a");
				headerField.setAccessible(true);
				headerField.set(packet, header);
				headerField.setAccessible(!headerField.isAccessible());
			}
			if(this.foot != null){
				Field footerField = packet.getClass().getDeclaredField("b");
				footerField.setAccessible(true);
				footerField.set(packet, footer);
				footerField.setAccessible(!footerField.isAccessible());
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		connection.sendPacket(packet);
	}
	public void broadcastTabMenu(){
	
			for (Player list : Bukkit.getOnlinePlayers()) {
			sendTo(list.getPlayer());
			}
			
	}
	
}

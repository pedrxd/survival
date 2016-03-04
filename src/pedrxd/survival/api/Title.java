package pedrxd.survival.api;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class Title {
	private int fadein, stay, fadeout;
	private String title, subtitle;
	public Title(){
		
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}
	public String getTitle(){
		return this.title;
	}
	public String getSubtitle(){
		return this.subtitle;
	}
	
	public void setFadeIn(int fadein){
		this.fadein = fadein;
	}
	public void setStay(int stay){
		this.stay = stay;
	}
	public void setFadeOut(int fadeout){
		this.fadeout = fadeout;
	}
	
	public int getFadeIn(){
		return this.fadein;
	}
	public int getStay(){
		return this.stay;
	}
	public int getFadeOut(){
		return this.fadeout;
	}
	
	public void sendTo(Player p){

		if(this.stay == 0){
			this.stay = 20;
		}
		if(this.subtitle == null){
			this.subtitle = " ";
		}
		if(this.title == null){
			this.title = " ";
		}
		
		 PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		 PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, this.fadein, this.stay, this.fadeout);
		 connection.sendPacket(packetPlayOutTimes);
		 
			 subtitle = subtitle.replaceAll("%player%", p.getDisplayName());
			 subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			 IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.subtitle + "\"}");
			 PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
			 connection.sendPacket(packetPlayOutSubTitle);
		 
		 
			 title = title.replaceAll("%player%", p.getDisplayName());
			 title = ChatColor.translateAlternateColorCodes('&', title);
			 IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.title + "\"}");
			 PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
			 connection.sendPacket(packetPlayOutTitle);
		 
	}
	public void broadcastTitle(){
		for (Player list : Bukkit.getOnlinePlayers()) {
			sendTo(list.getPlayer());
		}
	}
	
	
}

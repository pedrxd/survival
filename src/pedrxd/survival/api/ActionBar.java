package pedrxd.survival.api;


import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;



	public class ActionBar {
		private String message;
		public ActionBar(){
			
		}
		public void setMessage(String message){
			this.message = message;
		}
		public String getMessage(){
			return this.message;
		}
		public void broadcastActionBar(){
			for (Player list : Bukkit.getOnlinePlayers()) {
				sendTo(list.getPlayer());
			}
		}

		public void sendTo(Player p){
			CraftPlayer craftplayer = (CraftPlayer) p;
			IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message.replaceAll("%player%", p.getName())) + "\"}");
			PacketPlayOutChat packet = new PacketPlayOutChat(cbc, (byte) 2);
			craftplayer.getHandle().playerConnection.sendPacket(packet);
		}

		
}

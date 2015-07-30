package pedrxd.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pedrxd.survival.Players;
import pedrxd.survival.commands.CommandMute;
import pedrxd.survival.tools.PrivateMessage;
import pedrxd.survival.tools.TabListJoin;

public class OnChat extends Players implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		TabListJoin.onChat(e);
		PrivateMessage.PrivateMessage(e);
		CommandMute.onChat(e);
	}
}

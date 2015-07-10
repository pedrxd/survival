package pedrxd.survival;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pedrxd.survival.commands.*;
import pedrxd.survival.listeners.OnChat;
import pedrxd.survival.listeners.OnDeath;
import pedrxd.survival.listeners.OnInventory;
import pedrxd.survival.listeners.OnPlayerInteract;

public class Manager extends JavaPlugin {

	public static PluginFile home;
	public static PluginFile config;

	@Override
	public void onEnable() {
		registerEvent();
		configLoad();
		registerCommand();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label ,String[] args) {
		
		
		return true;
	}

	public void registerEvent() {

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new OnPlayerInteract(), this);
		pm.registerEvents(new OnChat(), this);
		pm.registerEvents(new OnDeath(), this);
		pm.registerEvents(new OnInventory(), this);

	}

	public void registerCommand() {

		getCommand("oi").setExecutor(new CommandOi());
		getCommand("tp").setExecutor(new CommandTp());
		getCommand("home").setExecutor(new CommandHome());
		getCommand("econfig").setExecutor(new CommandEconfig());
		getCommand("mute").setExecutor(new CommandMute());
		getCommand("creative").setExecutor(new CommandCreative());
		getCommand("survival").setExecutor(new CommandSurvival());
		getCommand("adventure").setExecutor(new CommandAdventure());
		getCommand("spec").setExecutor(new CommandSpectator());
		
	}

	public void configLoad() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		config = new PluginFile(this, "config.yml");
		home = new PluginFile(this, "home.yml");
	}

}

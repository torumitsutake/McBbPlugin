package com.gmail.sitoa.McbbPlugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class McBbPlugin extends JavaPlugin{
	private CommandClass cmd;
	private TeamBoard tb;
	public void onEnable(){
		cmd = new CommandClass(this);
		tb = new TeamBoard(this);
		getServer().getPluginManager().registerEvents(new ListenerClass(),this );
		getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		
	}
	
	public void onDisable(){
		
	}
	
	
	
	
	
	
	

}

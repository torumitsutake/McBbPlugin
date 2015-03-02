package com.gmail.sitoa.McbbPlugin;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Setting {
	Plugin p;
	String cmd;
	FileConfiguration cfg;
	Setting(String cmds, Plugin pl){
		cmd = cmds;
		p = pl;
		cfg = p.getConfig();
	}
	
	public void setSpawnPoint(String team,Location loc){
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		cfg.set("RespawnPoint."+team+".x", x);
		cfg.set("RespawnPoint."+team+".y", y);
		cfg.set("RespawnPoint."+team+".z", z);
	}
	public void setGameTime(int time){
		
		
	}
	
	

}

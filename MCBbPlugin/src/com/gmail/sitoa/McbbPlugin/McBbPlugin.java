package com.gmail.sitoa.McbbPlugin;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class McBbPlugin extends JavaPlugin{
	private CommandClass cmd;
	private TeamBoard tb;
	private McBbPlugin pl =  this;
	private ScoreBoardClass scb;
	public void onEnable(){
		cmd = new CommandClass(this);
		Bukkit.broadcastMessage("McBb loading...");
		getCommand("playerjoin").setExecutor(cmd);
		getCommand("gamestart").setExecutor(cmd);
		getCommand("gamestop").setExecutor(cmd);
		getCommand("setting").setExecutor(cmd);

		tb = new TeamBoard(this);
		getServer().getPluginManager().registerEvents(new ListenerClass(),this );
		getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		Bukkit.broadcastMessage("McBb load compleate!");
	}

	public void onDisable(){

	}

	public  void gamefinish(){

	}
	public  void start(){
		scb = new ScoreBoardClass(this);
		scb.connectSB();
		readytimer();

	}
	int readytimer = 0;
	public void readytimer(){
		readytimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int second = 10;
			@Override
			public void run() {
				if(second < 1){
					scb.starttimer(pl);
					Bukkit.getScheduler().cancelTask(readytimer);
				}
				for(Player target:getServer().getOnlinePlayers()){
					SendPacketClass.MakeTitlePacket(target, 5, 10, 5, String.valueOf(second), ChatColor.GREEN+"ゲーム開始まで,,,");

				}

				second--;
			}

		},0,20);
	}







}

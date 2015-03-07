package com.gmail.sitoa.McbbPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutNamedSoundEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;


public class McBbPlugin extends JavaPlugin{
	FileConfiguration config = this.getConfig();
	private CommandClass cmd;
	private TeamBoard tb;
	private McBbPlugin pl =  this;
	private ScoreBoardClass scb;
	public static boolean gamestart = false;
	public void onEnable(){
		cmd = new CommandClass(this);
		Bukkit.broadcastMessage("McBb loading...");
		getCommand("playerjoin").setExecutor(cmd);
		getCommand("gamestart").setExecutor(cmd);
		getCommand("gamestop").setExecutor(cmd);
		getCommand("setting").setExecutor(cmd);

		tb = new TeamBoard(this);
		getServer().getPluginManager().registerEvents(new ListenerClass(this),this );
		getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		Bukkit.broadcastMessage("McBb load compleate!");
	}

	public void onDisable(){

	}

	public  void gamefinish(){
		TeamBoard.clearTeam();
		gamestart = false;
		ScoreBoardClass.resetScore();
		for(Player target:getServer().getOnlinePlayers()){
			target.teleport(target.getWorld().getSpawnLocation());
			target.setBedSpawnLocation(target.getWorld().getSpawnLocation(),true);
			target.getInventory().clear();
		}
		getServer().getScheduler().cancelAllTasks();

	}
	public  void start(){
		gamestart = true;
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

					Team team = getServer().getScoreboardManager().getMainScoreboard().getTeam("Red");
					Location rloc = new Location(getServer().getWorld("world"),config.getInt("RespawnPoint.Red.x"), config.getInt("RespawnPoint.Red.y"), config.getInt("RespawnPoint.Red.z"));
					Location wloc = new Location(getServer().getWorld("world"),config.getInt("RespawnPoint.White.x"), config.getInt("RespawnPoint.White.y"), config.getInt("RespawnPoint.White.z"));
					for(OfflinePlayer target:team.getPlayers()){
						if(target.isOnline()){
						Player p= (Player)target;
						p.setBedSpawnLocation(rloc,true);
						p.teleport(rloc);
						p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);

						}
					}
					team=null;
					team = getServer().getScoreboardManager().getMainScoreboard().getTeam("White");
					for(OfflinePlayer target:team.getPlayers()){
						if(target.isOnline()){
						Player p= (Player)target;
						p.setBedSpawnLocation(wloc,true);
						p.teleport(wloc);
						p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
						}
					}
					for(Player p:getServer().getOnlinePlayers()){
						p.getInventory().clear();
						if(McBbPlugin.gamestart){
						ItemStack gure = new ItemStack(Material.FIREWORK_CHARGE,64);
						ItemStack dray = new ItemStack(Material.CLAY_BALL,3);
						ItemStack c4 = new ItemStack(Material.LEVER,1);
						ItemStack snowball = new ItemStack(Material.SNOW_BALL,64);
						dray.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
						gure.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
						c4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
						p.getInventory().addItem(gure);
						p.getInventory().addItem(gure);
						p.getInventory().addItem(dray);
						p.getInventory().addItem(c4);
						p.getInventory().addItem(snowball);
						p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE,5));
						
					}

					}
				}
				for(Player target:getServer().getOnlinePlayers()){
					target.playSound(target.getLocation(), Sound.NOTE_PIANO, 1, 1);
					SendPacketClass.MakeTitlePacket(target, 5, 15, 0, String.valueOf(second), ChatColor.GREEN+"ゲーム開始まで,,,");

				}

				second--;
			}

		},0,20);
	}







}

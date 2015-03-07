package com.gmail.sitoa.McbbPlugin;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ListenerClass implements Listener {
	private McBbPlugin p;
	ListenerClass(McBbPlugin pl){
		p=pl;
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		Team red = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("Red");

		Player p = e.getEntity();
		if(e.getDeathMessage().indexOf("slain") != -1){

			if(red.hasPlayer(p)){
				ScoreBoardClass.addScore("white", 1);
			}else{
				ScoreBoardClass.addScore("red", 1);
			}
		}


	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent  e){
		e.getPlayer().getInventory().clear();
		if(McBbPlugin.gamestart){
		ItemStack gure = new ItemStack(Material.FIREWORK_CHARGE,64);
		ItemStack dray = new ItemStack(Material.CLAY_BALL,3);
		ItemStack c4 = new ItemStack(Material.LEVER,1);
		ItemStack snowball = new ItemStack(Material.SNOW_BALL,64);
		dray.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		gure.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		c4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		e.getPlayer().getInventory().addItem(gure);
		e.getPlayer().getInventory().addItem(gure);
		e.getPlayer().getInventory().addItem(dray);
		e.getPlayer().getInventory().addItem(c4);
		e.getPlayer().getInventory().addItem(snowball);
		e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE,5));
	}
	}


}

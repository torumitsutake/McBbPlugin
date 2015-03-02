package com.gmail.sitoa.McbbPlugin;

import java.util.Collection;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandClass {
	Plugin p ;
	CommandClass(Plugin s){
		p =s;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("gamestart")){
		
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("gamestop")){
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("ranjoin")){
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("playerjoin")){
			//playerjoin [playername] team
			Player player = p.getServer().getPlayer(args[0]);
			String team = null;
			if(args[1].equalsIgnoreCase("red")){
				team="red";
				player.sendMessage(ChatColor.RED+"赤に所属しました。");
			}else if(args[1].equalsIgnoreCase("white")){
				team="white";
				player.sendMessage(ChatColor.WHITE+"白に所属しました。");
			}else{
				sender.sendMessage(ChatColor.RED+"チーム名はred/whiteで設定してください。");
			}
			
			if(!team.isEmpty()){
				TeamBoard.addPlayer(team, player);
			}else{
				System.out.println("errorcode:team unset");
			}
			
			
		}
		
		
		
		return false;
		
	}

}

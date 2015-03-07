package com.gmail.sitoa.McbbPlugin;


import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.Packet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClass implements CommandExecutor {
	McBbPlugin p ;
	CommandClass(McBbPlugin s){
		p =s;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("gamestart")){
		p.start();
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("gamestop")){
			p.gamefinish();
			return true;
		}

		if(cmd.getName().equalsIgnoreCase("ranjoin")){

			return true;
		}

		if(cmd.getName().equalsIgnoreCase("setting")){
			if(args[0].equalsIgnoreCase("test")){
				//PacketTest
			}

			if(args[0].equalsIgnoreCase("spawnpoint")){
				if(args[1].equalsIgnoreCase("white")){
					int x = Integer.valueOf(args[2]);
					int y= Integer.valueOf(args[3]);
					int z = Integer.valueOf(args[4]);
					p.getConfig().set("RespawnPoint.White.x", x);
					p.getConfig().set("RespawnPoint.White.y", y);
					p.getConfig().set("RespawnPoint.White.z", z);
					p.saveConfig();
					sender.sendMessage(ChatColor.RED+"設定完了しました。");

				}
				if(args[1].equalsIgnoreCase("red")){
					int x = Integer.valueOf(args[2]);
					int y= Integer.valueOf(args[3]);
					int z = Integer.valueOf(args[4]);
					p.getConfig().set("RespawnPoint.Red.x", x);
					p.getConfig().set("RespawnPoint.Red.y", y);
					p.getConfig().set("RespawnPoint.Red.z", z);
					sender.sendMessage(ChatColor.RED+"設定完了しました。");
					p.saveConfig();
					
				}else{
					sender.sendMessage(ChatColor.RED+"チーム名を正しく設定してください");
				}



			}


			if(args[0].equalsIgnoreCase("gametime")){
				int i = Integer.valueOf(args[1]);
				p.getConfig().set("GameTime", i);
				p.saveConfig();
				sender.sendMessage(ChatColor.RED+"設定完了しました。");
			}
			if(args[0].equalsIgnoreCase("winpoint")){
				int i = Integer.valueOf(args[1]);
				p.getConfig().set("WinPoint", i);
				p.saveConfig();
			}


			return true;
		}

		if(cmd.getName().equalsIgnoreCase("playerjoin")){


		}

		if(cmd.getName().equalsIgnoreCase("playerjoin")){
			//playerjoin [playername] team
			Player player = p.getServer().getPlayer(args[0]);
			String team = "red";
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

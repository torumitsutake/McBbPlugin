package com.gmail.sitoa.McbbPlugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamBoard {
	
	static Plugin p;
	TeamBoard(Plugin pl){
		p = pl;
		Scoreboard board = p.getServer().getScoreboardManager().getMainScoreboard();
		Team red = board.registerNewTeam("Red");
		Team white = board.registerNewTeam("White");
		red.setPrefix("§4");
		white.setPrefix("§f");
	}
	
	public static void addPlayer(String team,Player player){
		Scoreboard board = p.getServer().getScoreboardManager().getMainScoreboard();
		Team redteam = board.getTeam("Red");
		Team whiteteam = board.getTeam("White");
		Team teams;
		if(team.equalsIgnoreCase("red")){
			if(whiteteam.getPlayers().contains(player)){
				whiteteam.removePlayer(player);
			}
			teams = redteam;
		}else{
			if(redteam.getPlayers().contains(player)){
				redteam.removePlayer(player);
			}
			
			teams = whiteteam;
		}
		teams.addPlayer(player);
	}
	public static void removePlayer(String team,Player player){
		Scoreboard board = p.getServer().getScoreboardManager().getMainScoreboard();
		Team teams;
		if(team.equalsIgnoreCase("red")){
			teams = board.getTeam("Red");
		}else{
			teams = board.getTeam("White");
		}
		teams.removePlayer(player);
	}
	public static void clearTeam(){
		Scoreboard board = p.getServer().getScoreboardManager().getMainScoreboard();
		Team teams =  board.getTeam("Red");
		teams.unregister();
		teams =  board.getTeam("White");
		teams.unregister();
		Team red = board.registerNewTeam("Red");
		Team white = board.registerNewTeam("White");
		red.setPrefix("§4");
		white.setPrefix("§f");
		
	}
	
	

}

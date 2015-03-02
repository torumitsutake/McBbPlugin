package com.gmail.sitoa.McbbPlugin;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoardClass {
	int i=0;
	public String sb;
	ScoreboardManager manager;
	Scoreboard board ;
	
	Objective obj;
	Score score1 ;
	Score redticket;
	Score whiteticket;
	ScoreBoardClass(String sbname){
		sb = sbname;
	}
	public void connectSB(){
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		
		obj = board.registerNewObjective(ChatColor.AQUA+"GameInfo", "dummy");
		score1 = obj.getScore(ChatColor.GREEN+"GameTime:");
		redticket = obj.getScore(ChatColor.RED+"RedPoint");
		whiteticket = obj.getScore(ChatColor.WHITE+"WhitePoint");
		score1.setScore(1);
		redticket.setScore(2);
		whiteticket.setScore(3);
	}
	
	
	
	public void starttimer(final int sec,Plugin pl){
		 
		i = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			int second = sec;
			@Override
			public void run() {
				score1.setScore(second);
				
				if(second <= 0){
					
				}
				
				
				second--;
			}
			
		}, 0,20);
		
		
	}
	public static  void addScore(String team,int amount){
		String sco = "";
		if(team=="red"){
			sco=ChatColor.RED+"RedPoint";
		}else{
			sco=ChatColor.WHITE+"WhitePoint";
		}
		Score score = Bukkit.getScoreboardManager().getNewScoreboard().getObjective(ChatColor.AQUA+"GameInfo").getScore(sco);
		int scoreamount = score.getScore();
		scoreamount = scoreamount+amount;
		score.setScore(scoreamount);
	}
	
	

}

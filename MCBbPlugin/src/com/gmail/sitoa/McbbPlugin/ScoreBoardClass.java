package com.gmail.sitoa.McbbPlugin;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoardClass {
	static int i=0;
	ScoreboardManager manager;
	Scoreboard board ;

	Objective obj;
	 static McBbPlugin p;
	 Score score1 ;
	Score redticket;
	Score whiteticket;
	ScoreBoardClass(McBbPlugin pl){
		p = pl;
	}
	public void connectSB(){
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();

		obj = board.registerNewObjective(ChatColor.AQUA+"GameInfo", "dummy");
		score1 = obj.getScore(ChatColor.GREEN+"GameTime:");
		redticket = obj.getScore(ChatColor.RED+"RedPoint");
		whiteticket = obj.getScore(ChatColor.WHITE+"WhitePoint");
		score1.setScore(0);
		redticket.setScore(0);
		whiteticket.setScore(0);
		board.clearSlot(DisplaySlot.SIDEBAR);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

	}



	public  void starttimer(final Plugin pl){

		i = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {

			int second = pl.getConfig().getInt("GameTime");

			@Override
			public void run() {
				score1.setScore(second);

				if(second <= 0){
					Bukkit.getScheduler().cancelTask(i);
					if(redticket.getScore() > whiteticket.getScore()){
						Bukkit.broadcastMessage(ChatColor.RED+"Redチームの勝利です。");
					}else if(redticket.getScore() > whiteticket.getScore()){

					}else{
						Bukkit.broadcastMessage(ChatColor.AQUA+"引き分けです！");

					}
					Bukkit.broadcastMessage(ChatColor.AQUA+"ゲーム終了しました。");
					p.gamefinish();
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
		Score score = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(ChatColor.AQUA+"GameInfo").getScore(sco);
		int scoreamount = score.getScore();
		int i = p.getConfig().getInt("WinPoint");

		scoreamount = scoreamount+amount;
		if(i-scoreamount == 10){
			Bukkit.broadcastMessage(ChatColor.AQUA+team+"チームが残り10キルになりました。");
		}
		if(i-scoreamount <= 0){
			Bukkit.broadcastMessage(ChatColor.AQUA+team+"が、勝利キル数に達しました！");

			p.gamefinish();
		}

		score.setScore(scoreamount);
	}

	public static void resetScore(){
		Bukkit.getScoreboardManager().getMainScoreboard().getObjective(ChatColor.AQUA+"GameInfo").unregister();


	}
	}





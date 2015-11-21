package nl.rien_bijl.ScoreBoard.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import nl.rien_bijl.ScoreBoard.Color;
import nl.rien_bijl.ScoreBoard.Super;

public class Board {

	public static ScoreboardManager manager = Bukkit.getScoreboardManager();
	public static ArrayList<Player> noDisplay = new ArrayList<Player>();
	
	public static HashMap<Player, Scoreboard> boards = new HashMap<Player, Scoreboard>();
	
	public static void setBoardToPlayer(Player p)
	{
		
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) return;
		
		if(!noDisplay.contains(p)) return;
		
		ConfigurationSection config = Super.config.getConfigurationSection("scoreboard");
		
		Scoreboard board = boards.get(p);
		
		
		int r = new Random().nextInt(5999);
		int r2 = new Random().nextInt(5999);
		r = 1; r2 = 1;
		
		for(Objective unreg : board.getObjectives())
		{
			unreg.unregister();
		}
		
		Objective obj = board.registerNewObjective("sb" + r , r2 + "");
		

		
		if(obj.getDisplaySlot() != DisplaySlot.SIDEBAR)
		{
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		if(!obj.getDisplayName().equals(Color.color(Placeholder.placeholder(p, config.getString("header"))))){
			obj.setDisplayName(Color.color(Placeholder.placeholder(p, config.getString("header"))));
		}
		
		int length = config.getStringList("content").size();
		
		try{
			
			for(String s : config.getStringList("content"))
			{
				if(s.trim().equals("SPACER") || s.trim().isEmpty())
				{
					Score score = obj.getScore(CreateSpacer.createSpacer());
					score.setScore(length);
					
					length = length - 1;
				} else {
					
					Score score = obj.getScore(Color.color(Placeholder.placeholder(p, s)));
					score.setScore(length);
				
					length = length - 1;
				}
			}
			
			
			
			p.setScoreboard(board);
			
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}

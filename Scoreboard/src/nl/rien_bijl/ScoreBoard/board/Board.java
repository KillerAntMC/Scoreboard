package nl.rien_bijl.ScoreBoard.board;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.clip.placeholderapi.PlaceholderAPI;
import nl.rien_bijl.ScoreBoard.Color;
import nl.rien_bijl.ScoreBoard.Super;

public class Board {

	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	
	public static void setBoardToPlayer(Player p)
	{
		
		ConfigurationSection config = Super.config.getConfigurationSection("scoreboard");
		
		Scoreboard board = manager.getNewScoreboard(); 
		
		Objective obj = board.registerNewObjective("dash", "dummy");
		
		if(obj.getDisplaySlot() != DisplaySlot.SIDEBAR)
		{
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		obj.setDisplayName(Color.color(PlaceholderAPI.setPlaceholders(p, config.getString("header"))));
		
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
					Score score = obj.getScore(Color.color(PlaceholderAPI.setPlaceholders(p, s)));
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

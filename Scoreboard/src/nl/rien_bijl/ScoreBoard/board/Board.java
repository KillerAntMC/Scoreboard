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
	public static HashMap<Player, ObjectiveContainer> objectives = new HashMap<Player, ObjectiveContainer>();
	
	public static void setBoardToPlayer(Player p)
	{
		
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) return;
		
		if(!noDisplay.contains(p)) return;
		
		ConfigurationSection config = Super.config.getConfigurationSection("scoreboard");
		
		Scoreboard board = boards.get(p);
		
		if(Super.compatibilityMode == true)
		{
			if(p.getScoreboard() != null)
			{
				boards.remove(p);
				boards.put(p, p.getScoreboard());
				board = p.getScoreboard();
			}
		}
	
		
		ArrayList<String> objc = new ArrayList<String>();
		
		int r = new Random().nextInt(5999);
		int r2 = new Random().nextInt(5999);
		r = 1; r2 = 1;
		
			for(Objective unreg : board.getObjectives())
			{
				unreg.unregister();
			}
		
		Objective obj = board.registerNewObjective("sb" + r , r2 + "");
		int maxchars = Integer.parseInt(Super.config.getConfigurationSection("settings").getString("maxchars"));
		

		
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
					String spacer = CreateSpacer.createSpacer();
					if(spacer.length() > maxchars)
					{
						Score score = obj.getScore(spacer.substring(0, maxchars));
						score.setScore(length);
					} else {
						Score score = obj.getScore(spacer);
						score.setScore(length);
					}
					
					length = length - 1;
				} else {
					if(Placeholder.placeholder(p, s).length() > maxchars)
					{
						Score score = obj.getScore(Color.color(Placeholder.placeholder(p, s)).substring(0, maxchars));
						objc.add(Color.color(Placeholder.placeholder(p, s)).substring(0, maxchars));
						score.setScore(length);
					} else {
						
						Score score = obj.getScore(Color.color(Placeholder.placeholder(p, s)));
						objc.add(Color.color(Placeholder.placeholder(p, s)));
						score.setScore(length);
					}
				
					length = length - 1;
				}
			}
			
			boolean update = true;
			
			if(!Loop.force){
				
			if(Super.antiflicker == true)
			{
				if(objectives.containsKey(p))
				{
					ObjectiveContainer last = objectives.get(p);
					
					
					
					for(String s : last.scores)
					{
						if(!objc.contains(s))
						{
							if(last != null)
							{
								if(p.getScoreboard() != null)
								{
									update = false;
								}
							}
						} else {
						}
					}
					
					
					objectives.remove(p);
					objectives.put(p, new ObjectiveContainer(objc));
					
				} else {
					objectives.put(p, new ObjectiveContainer(objc));
				}
			}
			
			}
			Loop.force = false;
			
			if(!update){
				p.setScoreboard(board);
			}
			
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}

package nl.rien_bijl.ScoreBoard.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import nl.rien_bijl.ScoreBoard.Super;

public class Loop {

	public Loop()
	{
		if(Super.config.getConfigurationSection("settings").getString("anti-flicker").equalsIgnoreCase("true"))
		{
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncRepeatingTask(Super.plugin, new Runnable() {
	            @Override
	            public void run() {
	            	for(Player p : Bukkit.getOnlinePlayers())
			        {
			        	Board.setBoardToPlayer(p);
			        }
	            }
	        }, 0L, 10L);	
	        
		} else {
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncRepeatingTask(Super.plugin, new Runnable() {
	            @Override
	            public void run() {
	            	for(Player p : Bukkit.getOnlinePlayers())
			        {
			        	Board.setBoardToPlayer(p);
			        }
	            }
	        }, 0L, 100L);	
	        
		}
	}
	
}

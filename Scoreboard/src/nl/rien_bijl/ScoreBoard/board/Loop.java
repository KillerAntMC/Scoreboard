package nl.rien_bijl.ScoreBoard.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import nl.rien_bijl.ScoreBoard.Color;
import nl.rien_bijl.ScoreBoard.Super;

public class Loop {

	int count = 0;
	
	public static boolean force = true;
	
	public Loop()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(p.hasPermission("scoreboard.alerts"))
			{
				p.sendMessage(Color.color("&c&lScoreboard: &7Setting up loop (type:BukkitRunnable)"));
			}
		}
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncRepeatingTask(Super.plugin, new Runnable() {
	            @Override
	            public void run() {
	            	for(Player p : Bukkit.getOnlinePlayers())
			        {
			        	Board.setBoardToPlayer(p);
			        }
	            	if(force == true)
	            	{
	            		force = false;
	            	}
	            }
	        }, 0L, Super.updateTicks);	
	        
	
	}
	
}

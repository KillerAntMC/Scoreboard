package nl.rien_bijl.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import nl.rien_bijl.ScoreBoard.board.Board;
import nl.rien_bijl.ScoreBoard.board.Loop;
import nl.rien_bijl.ScoreBoard.command.Command;
import nl.rien_bijl.ScoreBoard.events.PlayerJoin;

public class Super extends JavaPlugin {
	
	public static FileConfiguration config = null;
	public static Super plugin;
	
	public static boolean antiflicker = true;
	public static int updateTicks = 10;

	public void onEnable()
	{
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getCommand("scoreboard").setExecutor(new Command());
		
		
		config = getConfig();
		plugin = this;
		
		initiateSettings();
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			Board.boards.put(p, Board.manager.getNewScoreboard());
			Board.noDisplay.add(p);
		}
		
		new Loop();
	}
	
	public void reloadConfigPlugin()
	{
		reloadConfig();
		initiateSettings();
	}
	
	public void initiateSettings()
	{
		if(getConfig().getConfigurationSection("anti-flicker").getString("enabled").equalsIgnoreCase("true"))
		{
			antiflicker = true;
		}
		
		if(getConfig().getConfigurationSection("settings").contains("anti-flicker"))
		{
			System.out.println("=-=-=-=-=-[Scoreboard]-=-=-=-=-");
			System.out.println("Configuration out to date, trying to fix.");
			getConfig().getConfigurationSection("settings").set("anti-flicker", "No longer in use.");
			System.out.println("Added warning.");
		}
		
		updateTicks = getConfig().getConfigurationSection("settings").getInt("update_time_ticks");
		
	}
	
}

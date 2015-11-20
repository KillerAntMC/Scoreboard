package nl.rien_bijl.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import nl.rien_bijl.ScoreBoard.board.Loop;
import nl.rien_bijl.ScoreBoard.command.Command;
import nl.rien_bijl.ScoreBoard.events.PlayerJoin;

public class Super extends JavaPlugin {
	
	public static FileConfiguration config = null;
	public static Super plugin;

	public void onEnable()
	{
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getCommand("scoreboard").setExecutor(new Command());
		
		config = getConfig();
		plugin = this;
		
		new Loop();
	}
	
}

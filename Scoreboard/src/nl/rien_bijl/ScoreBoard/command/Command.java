package nl.rien_bijl.ScoreBoard.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import nl.rien_bijl.ScoreBoard.Color;
import nl.rien_bijl.ScoreBoard.Super;
import nl.rien_bijl.ScoreBoard.board.Board;
import nl.rien_bijl.ScoreBoard.board.Loop;

public class Command implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command arg1, String arg2, String[] args) {
		
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
		{
			cs.sendMessage(Color.color("&c&lHold up! &7You don't have PlaceholderAPI installed!"));
		}
		
		if(args.length < 1)
		{
			cs.sendMessage(Color.color("&c&lScoreboard commands"));
			cs.sendMessage(Color.color("&7Scoreboard made by HelloitsRien, 100% free on spigot!"));
			cs.sendMessage(Color.color("&c/scoreboard show &7(See board in the chat)"));
			cs.sendMessage(Color.color("&c/scoreboard reload &7(Reload the configuration file)"));
			cs.sendMessage(Color.color("&c/scoreboard toggle &7(Toggle the scoreboard)"));
		} else {
			if(args[0].equalsIgnoreCase("show"))
			{
				if(!(cs instanceof Player))
				{
					cs.sendMessage("Only a player can execute this");
					return false;
				}
				ConfigurationSection config = Super.config.getConfigurationSection("scoreboard");
				
				cs.sendMessage(Color.color(PlaceholderAPI.setPlaceholders((Player) cs, config.getString("header"))));
				
				for(String s : config.getStringList("content"))
				{
					if(s.trim().equals("SPACER"))
					{
						cs.sendMessage("");
						continue;
					}
					cs.sendMessage(Color.color(PlaceholderAPI.setPlaceholders((Player) cs, s)));
				}
				
			} else if(args[0].equalsIgnoreCase("reload"))
			{
				if(cs.hasPermission("scoreboard.reload")){
				Super.plugin.reloadConfigPlugin();
				Super.plugin.reloadConfig();
				Super.plugin.reloadConfigPlugin();
				
				Super.plugin.saveConfig();
				cs.sendMessage(Color.color("&cScoreboard's configuration is reloaded"));
				Loop.force = true;
				} else{
					cs.sendMessage(Color.color("&cYou do not have permission!"));
				}
			} else if(args[0].equalsIgnoreCase("toggle"))
			{
				if(cs.hasPermission("scoreboard.toggle"))
				{
					if(cs instanceof Player)
					{
						if(!Board.noDisplay.contains((Player) cs))
						{
							Board.noDisplay.add((Player) cs);
							cs.sendMessage(Color.color("&cYou enabled the scoreboard"));
						} else {
							Board.noDisplay.remove((Player) cs);
							cs.sendMessage(Color.color("&cYou disabled the scoreboard"));
							Player p = (Player) cs;
							p.setScoreboard(Board.manager.getNewScoreboard());
						}
					} else {
						cs.sendMessage("Only a player can execute this");
					}
				} else {
					cs.sendMessage(Color.color("&cYou do not have permission!"));
				}
			}
			
		else {
				cs.sendMessage(Color.color("&cInvalid argument"));
			}
		}
		
		return false;
	}

}

package nl.rien_bijl.ScoreBoard.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import nl.rien_bijl.ScoreBoard.Color;
import nl.rien_bijl.ScoreBoard.Super;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command arg1, String arg2, String[] args) {
		
		if(args.length < 1)
		{
			cs.sendMessage(Color.color("&c&lScoreboard commands"));
			cs.sendMessage(Color.color("&7Scoreboard made by HelloitsRien, 100% free on spigot!"));
			cs.sendMessage(Color.color("&c/scoreboard show &7(See board in the chat)"));
			cs.sendMessage(Color.color("&c/scoreboard reload &7(Reload the configuration file)"));
		} else {
			if(args[0].equalsIgnoreCase("show"))
			{
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
				Super.plugin.reloadConfig();
				Super.config = Super.plugin.getConfig();
				Super.plugin.reloadConfig();
				Super.plugin.saveConfig();
				cs.sendMessage(Color.color("&cScoreboard's configuration is reloaded"));
				} else{
					cs.sendMessage(Color.color("&cYou do not have permission!"));
				}
			} else {
				cs.sendMessage(Color.color("&cInvalid argument"));
			}
		}
		
		return false;
	}

}
package nl.rien_bijl.ScoreBoard.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class Placeholder {

	public static String placeholder(Player p,String s)
	{
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
		{
			return PlaceholderAPI.setPlaceholders(p, s);
		} else {
			return s;
		}
	}
}

package nl.rien_bijl.ScoreBoard.board;

import java.util.Random;

import org.bukkit.ChatColor;


public class CreateSpacer {

	
	public static String createSpacer()
	{
		String build = "";
		
		for(int i = 0; i < 15; i++)
		{
			build = add(build);
		}
		
		return build;
		
	}
	
	private static String add(String build)
	{
		Random random = new Random();
		
		int r = random.nextInt(7) + 1;
		
		build = build + ChatColor.values()[r];
		
		return build;
	}
	
}

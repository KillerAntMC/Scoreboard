package nl.rien_bijl.ScoreBoard.board;

import java.util.Random;

import net.md_5.bungee.api.ChatColor;

public class CreateSpacer {

	
	public static String createSpacer()
	{
		String build = "";
		
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		build = add(build);
		
		return build;
		
	}
	
	private static String add(String build)
	{
		Random random = new Random();
		
		int r = random.nextInt(9) + 1;
		
		if(r == 1)
		{
			build = build + ChatColor.RED;
		}
		if(r == 2)
		{
			build = build + ChatColor.AQUA;
		}
		if(r == 3)
		{
			build = build + ChatColor.BLUE;
		}
		if(r == 4)
		{
			build = build + ChatColor.DARK_BLUE;
		}
		if(r == 5)
		{
			build = build + ChatColor.AQUA;
		}
		if(r == 6)
		{
			build = build + ChatColor.GRAY;
		}
		if(r == 7)
		{
			build = build + ChatColor.WHITE;
		}
		if(r == 8)
		{
			build = build + ChatColor.DARK_AQUA;
		}
		if(r == 9)
		{
			build = build + ChatColor.DARK_BLUE;
		}
		if(r == 10)
		{
			build = build + ChatColor.DARK_GREEN;
		}
		if(r == 11)
		{
			build = build + ChatColor.DARK_PURPLE;
		}
		
		
		return build;
	}
	
}

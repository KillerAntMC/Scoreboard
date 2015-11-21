package nl.rien_bijl.ScoreBoard.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.rien_bijl.ScoreBoard.board.Board;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Board.noDisplay.add(e.getPlayer());
		Board.setBoardToPlayer(e.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		Board.noDisplay.remove(e.getPlayer());
	}
}

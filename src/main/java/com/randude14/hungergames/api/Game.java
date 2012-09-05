package com.randude14.hungergames.api;

import com.randude14.hungergames.core.LocalPlayer;
import com.randude14.hungergames.stats.PlayerStat;
import com.randude14.hungergames.utils.Cuboid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Chest;

public interface Game {
	public boolean isSpectating(LocalPlayer player);

	public boolean stopGame(LocalPlayer player, boolean isFinished);
	
	public String stopGame(boolean isFinished);
	
	/**
	 * Starts the game with the specified number of ticks
	 * 
	 * @param player
	 * @param ticks
	 * @return true if game or countdown was successfully started
	 */
	public boolean startGame(LocalPlayer player, int ticks);
	
	/**
	 * Starts this game with the default time if immediate is true. Otherwise, starts the game immediately.
	 * 
	 * @param notifier who to notify
	 * @param immediate
	 * @return
	 */	
	public boolean startGame(LocalPlayer notifier, boolean immediate);

	/**
	 * Starts this game with the default time if immediate is true. Otherwise, starts the game immediately.
	 * 
	 * @param immediate
	 * @return
	 */	
	public boolean startGame(boolean immediate);
	
		
	/**
	 * Starts the game
	 * 
	 * @param ticks 
	 * @return Null if game or countdown was successfully started. Otherwise, error message.
	 */
	public String startGame(int ticks);

	
 	public boolean resumeGame(LocalPlayer player, int ticks);	
	
	public boolean resumeGame(LocalPlayer player, boolean immediate);
	
	public boolean resumeGame(boolean immediate);
	
	/**
	 * Resumes the game
	 * 
	 * @param ticks 
	 * @return Null if game or countdown was not successfully started. Otherwise, error message.
	 */
	public String resumeGame(int ticks);
	
	public boolean pauseGame(LocalPlayer notifier);
	
	/**
	 * 
	 * @return null if successful, message if not
	 */
	public String pauseGame();
	
	public void addAndFillChest(Chest chest);
        
	public void fillInventories();
	
	/**
	 * Only used for players that have left the game, but not quitted. Only valid while game is running
	 * 
	 * @param player
	 * @return true if successful
	 */
	public boolean rejoin(LocalPlayer player);

	public boolean join(LocalPlayer player);
	
	public boolean leave(LocalPlayer player, boolean callEvent);
	
	public boolean quit(LocalPlayer player, boolean callEvent);
	
	/**
	 * Will be canceled if player is playing and teleporting is not allowed which should not ever happen
	 * @param player
	 */
	public void teleportPlayerToSpawn(LocalPlayer player);
	
	/**
	 * 
	 * @param notifyOfRemaining
	 * @return true if is over, false if not
	 */
	public boolean checkForGameOver(boolean notifyOfRemaining);
	
	public String getInfo();
	
	/**
	 * Checks if players are in the game and have lives, regardless is game is running and if they are playing.
	 * @param players players to check
	 * @return
	 */
	public boolean contains(LocalPlayer... players);
	
	/**
	 * 
	 * @param players players to check
	 * @return true if players are in the game, have lives, and are playing
	 */
	public boolean isPlaying(LocalPlayer... players);

	/**
	 * Gets the players that have lives and are playing
	 * If game is not yet started remaining players are those that are waiting
	 * 
	 * @return the remaining players that have lives and are playing
	 */
	public List<LocalPlayer> getRemainingPlayers();
	
	public PlayerStat getPlayerStat(OfflinePlayer player);
	
	public void listStats(LocalPlayer player);
	
	public String getName();

	public boolean addChest(Location loc, float weight);

	public boolean addFixedChest(Location loc, String fixedChest);

	public boolean addSpawnPoint(Location loc);

	/**
	 * Removes chest from fixedChests and adds it to chests
	 * @param loc
	 * @return
	 */
	public boolean removeFixedChest(Location loc);

	public boolean removeChest(Location loc);

	public boolean removeSpawnPoint(Location loc);

	public void setEnabled(boolean flag);
	
	public void setSpawn(Location newSpawn);

	public List<String> getAllPlayers();

	public List<PlayerStat> getStats();
	
	public Location getSpawn();

	public String getSetup();

	public List<String> getItemSets();

	public void addItemSet(String name);

	public void removeItemSet(String name);
		
	public void addWorld(World world);

	public void addCuboid(Location one, Location two);

	public Map<String, List<String>> getSponsors();
	
	public Set<World> getWorlds();
	
	public Set<Cuboid> getCuboids();
	
	public void removeItemsOnGround();
	
	public int getSize();

	public void playCannonBoom();

	public List<Long> getEndTimes();

	public long getInitialStartTime();

	public List<Long> getStartTimes();
	
	public GameState getState();
	
	public enum GameState {
		DISABLED,
		DELETED,
		STOPPED,
		RUNNING,
		PAUSED,
		COUNTING_FOR_START,
		COUNTING_FOR_RESUME,
		ABOUT_TO_START;
		
	}
}

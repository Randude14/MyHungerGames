package com.randude14.hungergames.commands.admin;

import com.randude14.hungergames.Defaults;
import com.randude14.hungergames.Defaults.Perm;
import com.randude14.hungergames.GameManager;
import com.randude14.hungergames.HungerGames;
import com.randude14.hungergames.commands.Command;
import com.randude14.hungergames.utils.ChatUtils;

import org.bukkit.command.CommandSender;

public class PauseCommand extends Command {

	public PauseCommand() {
		super(Perm.ADMIN_PAUSE, "pause", ADMIN_COMMAND);
	}

	@Override
	public void handle(CommandSender cs, String label, String[] args) {

		String name = (args.length < 1) ? Defaults.Config.DEFAULT_GAME.getGlobalString() : args[0];
		if (name == null) {
			ChatUtils.helpCommand(cs, getUsage(), HungerGames.CMD_ADMIN);
			return;
		}

		game = HungerGames.getInstance().getGameManager().getRawGame(name);
		if (game == null) {
		    ChatUtils.error(cs, "%s does not exist.", name);
		    return;
		}

		if(game.pauseGame(cs)) {
			ChatUtils.broadcast(game, "%s has been paused.", game.getName());
		}
		return;
	}

	@Override
	public String getInfo() {
		return "pause a game";
	}

	@Override
	protected String getPrivateUsage() {
		return "pause [game name]";
	}
    
}

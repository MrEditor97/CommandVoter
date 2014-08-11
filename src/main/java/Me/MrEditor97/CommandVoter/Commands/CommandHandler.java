package Me.MrEditor97.CommandVoter.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;

public class CommandHandler implements CommandExecutor {
	
	CommandVoter plugin;
	
	public CommandHandler(CommandVoter instance) {
		plugin = instance;	
	}
	
	//OnCommand
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Vote vote = new Vote(plugin);
		TopVoters topvoters = new TopVoters(plugin);
		Rewards rewards = new Rewards(plugin);
		
		Message message = new Message(plugin);
		
		//Check if the command sender was a player
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			//Check if the command was /vote
			if (cmd.getName().equalsIgnoreCase("vote")) {
				//Check if the player has permission
				if (player.hasPermission("commandvoter.vote")) {
					vote.command(sender);
					return true;
				} else {
					//Message player
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			//Check if the command was /topvoters
			} else if (cmd.getName().equalsIgnoreCase("topvoters")) {
				//Check if the player has permission
				if (player.hasPermission("commandvoter.topvoters")) {
					topvoters.command(sender);
					return true;
				} else {
					//Message player
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			//Check if the command was /rewards
			} else if (cmd.getName().equalsIgnoreCase("rewards")) {
				//Check if the player has permission
				if (player.hasPermission("commandvoter.rewards.other")) {
					//Reward player who has been specified
					rewards.command(true, sender, cmd, label, args);
					return true;
				//Check if the player has permission
				} else if (player.hasPermission("commandvoter.rewards")) {
					//Reward player who performed the command
					rewards.command(false, sender, cmd, label, args);
					return true;
				} else {
					//Message player
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			}
		}
		return false;
	}
}

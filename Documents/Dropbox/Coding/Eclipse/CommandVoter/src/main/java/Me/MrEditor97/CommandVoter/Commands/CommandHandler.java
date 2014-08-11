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
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("vote")) {
				if (player.hasPermission("commandvoter.vote")) {
					vote.command(sender);
					return true;
				} else {
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			} else if (cmd.getName().equalsIgnoreCase("topvoters")) {
				if (player.hasPermission("commandvoter.topvoters")) {
					topvoters.command(sender);
					return true;
				} else {
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			} else if (cmd.getName().equalsIgnoreCase("rewards")) {
				if (player.hasPermission("commandvoter.rewards.other")) {
					//Reward player who has been specified
					rewards.command(true, sender, cmd, label, args);
					return true;
				} else if (player.hasPermission("commandvoter.rewards")) {
					//Reward player who performed the command
					rewards.command(false, sender, cmd, label, args);
					return true;
				} else {
					message.permission(sender, plugin.getConfig().getString("messages.permission"));
					return false;
				}
			}
		}
		return false;
	}
//	
//	//Vote Command
//	private void voteCommand(CommandSender sender) {
//		timesVoted.clear();
//		
//		plugin.getConfig().getStringList("votelinks");
//		
//		for (int i = 0; i <= plugin.getConfig().getStringList("votelinks").size() - 1; i++) {
//			Message.playerVoteLinks(sender, plugin.getConfig().getStringList("votelinks").get(i), plugin.getConfig().getInt("players." + sender.getName() + ".voted"));
//		}		
//	}
//	
//	//TopVoters Command
//	private void topVotersCommand(CommandSender sender) {
//		for (String key : plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
//			//Add Voted to HashMap
//			timesVoted.put(plugin.getConfig().getInt("players." + key + ".voted"), key + "");
//		}
//		//Convert data into a Sorted Map
//		Map<Integer, String> sortedVoters = sortByKeys(timesVoted);
//		
//		//Pass parameters to topVoters, to pass to player.
//		Message.topVoters(sender, sortedVoters, plugin.getConfig().getInt("listTopPlayers"), plugin.getConfig().getString("playerTopVoterMessage"));
//	}

}

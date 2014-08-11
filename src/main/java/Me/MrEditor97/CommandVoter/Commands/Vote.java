package Me.MrEditor97.CommandVoter.Commands;

import org.bukkit.command.CommandSender;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;

public class Vote {
	
	CommandVoter plugin;
	
	public Vote(CommandVoter instance) {
		plugin = instance;
	}
	
	//When a player performs a command
	public void command(CommandSender sender) {
		Message message = new Message(plugin);
		
		//List the voting links
		for (int i = 0; i <= plugin.getConfig().getStringList("votelinks").size() - 1; i++) {
			//Message player
			message.vote(sender, plugin.getConfig().getStringList("votelinks").get(i), plugin.getConfig().getInt("players." + sender.getName().toLowerCase() + ".voted"));
		}	
	}

}

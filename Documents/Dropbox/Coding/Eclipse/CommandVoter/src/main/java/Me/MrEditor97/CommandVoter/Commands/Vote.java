package Me.MrEditor97.CommandVoter.Commands;

import org.bukkit.command.CommandSender;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;

public class Vote {
	
	CommandVoter plugin;
	
	public Vote(CommandVoter instance) {
		plugin = instance;
	}
	
	public void command(CommandSender sender) {
		Message message = new Message(plugin);
		
		for (int i = 0; i <= plugin.getConfig().getStringList("votelinks").size() - 1; i++) {
			message.vote(sender, plugin.getConfig().getStringList("votelinks").get(i), plugin.getConfig().getInt("players." + sender.getName().toLowerCase() + ".voted"));
		}	
	}

}

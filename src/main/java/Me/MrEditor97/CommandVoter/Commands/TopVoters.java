package Me.MrEditor97.CommandVoter.Commands;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.command.CommandSender;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;

public class TopVoters {
	
	Map<String, Integer> timesVoted = new TreeMap<String, Integer>();
	
	CommandVoter plugin;
	
	public TopVoters(CommandVoter instance) {
		plugin = instance;
	}
	
	//When a player performs the command
	public void command(CommandSender sender) {
		Message message = new Message(plugin);
		
		timesVoted.clear();
		
		//Add players into a Map
		for (String key : plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
			timesVoted.put(plugin.getConfig().getString("players." + key.toLowerCase() + ".username"), plugin.getConfig().getInt("players." + key.toLowerCase() + ".voted"));
		}
		
		//Message player
		message.top(sender, timesVoted, plugin.getConfig().getString("playerTopVoterMessage"), plugin.getConfig().getInt("listTopVoters"));
	}

}

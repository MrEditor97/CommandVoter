package Me.MrEditor97.CommandVoter.Rewards;

import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;
import Me.MrEditor97.CommandVoter.Util.Util;

public class Level {
	
	CommandVoter plugin;
	
	public Level(CommandVoter instance) {
		plugin = instance;
	}
	
	//Get the Reward Level the player is on
	public int getLevel(Player target) {
		Util util = new Util(plugin);
		Message message = new Message(plugin);
		
		//Get Reward's
		int size = plugin.getConfig().getConfigurationSection("rewards").getKeys(false).size();
		String[] levels = new String[size];
		size = -1;
		
		//Add the Rewards to a String list (Level)
		for (String level : plugin.getConfig().getConfigurationSection("rewards").getKeys(false)) {
			size++;
			levels[size] = level;	
		}
		
		//For levels length repeat until found correct Level
		for (int i = 0; i <= levels.length; i++) {
			int timesVoted = plugin.getConfig().getInt("players." + target.getName().toLowerCase() + ".voted");
			
			//If times voted != 0
			if (timesVoted != 0) {
				//If times voted is greater than levels[0] (the minium level)
				if (timesVoted >= Integer.parseInt(levels[0])) {
					//If times voted is more than levels[size] (the highest level)
					if (timesVoted >= Integer.parseInt(levels[size])) {
						//Return the highest level for voting
						return util.parseInt(levels[size]);
					} else {
						//If times voted is less than levels[i]
						if (timesVoted < Integer.parseInt(levels[i])) {
							//Return the level
							return util.parseInt(levels[i -1]);
						}
					}
				} else {
					//Message player, with the message as defined in the configuration file
					message.send(target, plugin.getConfig().getString("messages.noRewardsOther"));
					break;
				}
			} else {
				//Message player, with the message as defined in the configuration file
				message.send(target, plugin.getConfig().getString("messages.noRewards"));
				break;
			}
		}
		return 0;
	}
}

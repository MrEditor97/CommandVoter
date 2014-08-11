package Me.MrEditor97.CommandVoter.Rewards;

import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;
import Me.MrEditor97.CommandVoter.Util.Inventory;

public class Reward {
	
	CommandVoter plugin;
	
	public Reward(CommandVoter instance) {
		plugin = instance;
	}

	public void get(Player sender) {
		Level level = new Level(plugin);
		Message message = new Message(plugin);
		Inventory inv = new Inventory(plugin);
		
		//Get Reward Level
		int rewardLevel = level.getLevel(sender);
		
		//Check if reward level doesnt equal zero
		if (rewardLevel != 0) {
			//Check if player has already been rewarded for there current vote
			if (plugin.getConfig().getBoolean("players." + sender.getName().toLowerCase() + ".rewarded") != true) {
				//Check if player has space in there inventory
				if (inv.hasSpace(sender, plugin.getConfig().getStringList("rewards." + rewardLevel + "").size()) == true) {
					//Add items to Players inventory
					if (inv.addItems(sender, rewardLevel) == true) {
						//Message the player to say they have been rewarded
						message.rewardPlayer(sender, plugin.getConfig().getString("messages.rewardMessage"));
						//Once they have been rewarded, change player rewarded to true
						plugin.getConfig().set("players." + sender.getName().toLowerCase() + ".rewarded", true);
						
						//Copy and Save for config.yml
						plugin.getConfig().options().copyDefaults(true);
						plugin.saveConfig();
					}
				} else {
					//Not enough space in players inventory
					message.send(sender, plugin.getConfig().getString("messages.notEnoughSpace"));
				}			
			} else {
				//Player has already been Rewarded for there current vote
				message.send(sender, plugin.getConfig().getString("messages.alreadyRewarded"));
			}
		} else {
			//No Rewards
			message.send(sender, plugin.getConfig().getString("messages.noRewards"));
		}
	}

	public void getOther(Player sender, Player target) {
		Level level = new Level(plugin);
		Message message = new Message(plugin);
		Inventory inv = new Inventory(plugin);
		
		//Get Reward Level
		int rewardLevel = level.getLevel(sender);
		
		//Check if reward level doesnt equal zero
		if (rewardLevel != 0) {
			//Check if player has already been rewarded for there current vote
			if (plugin.getConfig().getBoolean("players." + target.getName().toLowerCase() + ".rewarded") != true) {
				//Check if player has space in there inventory
				if (inv.hasSpace(target, plugin.getConfig().getStringList("rewards." + rewardLevel + "").size()) == true) {
					//Add items to Players inventory
					if (inv.addItems(target, rewardLevel) == true) {
						//Message the player to say they have been rewarded
						message.rewardTarget(sender, target, plugin.getConfig().getString("messages.rewardMessage"), plugin.getConfig().getString("messages.rewardMessageSender"));
						//Once they have been rewarded, change player rewarded to true
						plugin.getConfig().set("players." + target.getName().toLowerCase() + ".rewarded", true);
						
						//Copy and Save for config.yml
						plugin.getConfig().options().copyDefaults(true);
						plugin.saveConfig();
					}
				} else {
					//Not enough space in players inventory
					message.send(sender, plugin.getConfig().getString("messages.notEnoughSpaceOther"));
				}			
			} else {
				//Player has already been Rewarded for there current vote
				message.send(sender, plugin.getConfig().getString("messages.alreadyRewardedOther"));
			}
		} else {
			//No Rewards
			message.send(sender, plugin.getConfig().getString("messages.noRewardsOther"));
		}
	}
}

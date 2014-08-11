package Me.MrEditor97.CommandVoter.Messages;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Util.Util;

public class Message {
	
	CommandVoter plugin;
	
	public Message(CommandVoter instance) {
		this.plugin = instance;
	}
	
	//Used on the vote command
	public void vote(CommandSender sender, String message, int voted) {
		Util util = new Util(plugin);		
		sender.sendMessage(util.colorize(util.playerize(util.voterize(message, voted), (Player) sender)));
	}
	
	//Used on the topvoters command
	public void top(CommandSender sender, Map<String, Integer> timesVoted, String message, int topVotersLength) {
		Util util = new Util(plugin);
		
		int topPlayers = plugin.getConfig().getInt("listTopVoters");
		int i = 0;
		
		for (Map.Entry<String, Integer> entry : util.sortByValues(timesVoted).entrySet()) {
			if (i <= topPlayers) {
				i++;
				sender.sendMessage(util.colorize(util.stringPlayerize(util.voterize(message, entry.getValue()), entry.getKey())));
			}
		}
	}
	
	//Used on player join event
	public void join(Player player, String message, int timesVoted) {
		Util util = new Util(plugin);
		
		if (!message.equalsIgnoreCase("false")) {
			player.sendMessage(util.colorize(util.playerize(util.voterize(message, timesVoted), player)));
		}
	}
	
	//Used on player voted event
	public void voted(String player, String message, int timesVoted) {
		Util util = new Util(plugin);
		
		if (!message.equalsIgnoreCase("false")) {
			plugin.getServer().broadcastMessage(util.colorize(util.stringPlayerize(util.voterize(message, timesVoted), plugin.getConfig().getString("players." + player.toLowerCase() + ".username"))));
		}
	}
	
	//Used on player online event
	public void online(Player player, String message, String name) {
		Util util = new Util(plugin);
		player.sendMessage(util.colorize(util.stringPlayerize(message, name)));
	}
	
	//Used on player permission event
	public void permission(CommandSender sender, String message) {
		Util util = new Util(plugin);
		sender.sendMessage(util.colorize(message));
	}
	
	//Used on player reward even
	public void reward(boolean reward, CommandSender sender, Player target, String message) {
		Util util = new Util(plugin);
		
		if (!reward == true) {
			sender.sendMessage(util.colorize(util.playerize(message, target)));
		} else {
			
		}
	}
	
	//Used on player reward target
	public void rewardTarget(Player sender, Player target, String messageTarget, String messageSender) {
		Util util = new Util(plugin);
		
		target.sendMessage(util.colorize(util.playerize(messageTarget, target)));
		
		rewardPlayer(sender, messageSender);
	}
	
	//Used on player reward player (sender)
	public void rewardPlayer(Player player, String message) {
		Util util = new Util(plugin);
		player.sendMessage(util.colorize(util.stringPlayerize(message, player.getName())));
	}
	
	//Used on message send
	public void send(Player player, String message) {
		Util util = new Util(plugin);
		player.sendMessage(util.colorize(util.playerize(message, player)));
	}
}

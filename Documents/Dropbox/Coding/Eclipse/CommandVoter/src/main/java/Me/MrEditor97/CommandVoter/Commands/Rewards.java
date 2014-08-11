package Me.MrEditor97.CommandVoter.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;
import Me.MrEditor97.CommandVoter.Rewards.Reward;

public class Rewards {
	
	CommandVoter plugin;
	
	public Rewards(CommandVoter instance) {
		plugin = instance; 
	}

	@SuppressWarnings("deprecation")
	public void command(boolean permission, CommandSender sender, Command cmd, String label, String[] args) {
		Message message = new Message(plugin);
		Reward reward = new Reward(plugin);
		
		Player player = (Player) sender;
		
		if (args.length == 0) {
			reward.get(player);
		} else {
			if (permission = true) {
				Player target = plugin.getServer().getPlayer(args[0]);
				
				if (target == null) {
					message.online(player, plugin.getConfig().getString("messages.playerNotOnline"), args[0]);
				} else {
					reward.getOther(player, target);
				}
			} else if (permission = false) {
				message.permission(player, plugin.getConfig().getString("messages.permission"));
			}
		}
	}
}

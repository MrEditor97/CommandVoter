package Me.MrEditor97.CommandVoter.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Rewards.Reward;

public class Rewards {
	
	CommandVoter plugin;
	
	public Rewards(CommandVoter instance) {
		plugin = instance; 
	}
	
	//When the player peforms the command
//	@SuppressWarnings("deprecation")
	public void command(boolean permission, CommandSender sender, Command cmd, String label, String[] args) {
//		Message message = new Message(plugin);
		Reward reward = new Reward(plugin);
		
		Player player = (Player) sender;
		
		//If a player has only typed /rewards
		if (args.length == 0) {
			reward.get(player);
		} // else {
//			//Check if the player has permission to do /rewards [player]
//			if (permission = true) {
//				Player target = plugin.getServer().getPlayer(args[0]);
//				
//				if (target == null) {
//					message.online(player, plugin.getConfig().getString("messages.playerNotOnline"), args[0]);
//				} else {
//					reward.getOther(player, target);
//				}
//			} else if (permission = false) {
//				//Message player
//				message.permission(player, plugin.getConfig().getString("messages.permission"));
//			}
//		}
	}
}

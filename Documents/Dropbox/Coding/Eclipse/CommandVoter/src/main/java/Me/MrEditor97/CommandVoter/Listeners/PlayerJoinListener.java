package Me.MrEditor97.CommandVoter.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;
import Me.MrEditor97.CommandVoter.Util.Util;

public class PlayerJoinListener implements Listener {
	
	CommandVoter plugin;
	
	public PlayerJoinListener(CommandVoter instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Message message = new Message(plugin);
		Util util = new Util(plugin);
		
		Player player = event.getPlayer();
		
		if(util.nodeExists(plugin.getConfig(), "players." + player.getName().toLowerCase()) == true) {
			String joinMessage = plugin.getConfig().getString("playerJoinMessage");
			int timesVoted = plugin.getConfig().getInt("players." + player.getName().toLowerCase() + ".voted");
			
			message.join(player, joinMessage, timesVoted);
		} else {
			plugin.getConfig().set("players." + player.getName().toLowerCase() + ".username", player.getName());
			plugin.getConfig().set("players." + player.getName().toLowerCase() + ".uuid", player.getUniqueId().toString());
			plugin.getConfig().set("players." + player.getName().toLowerCase() + ".voted", 0);
			plugin.getConfig().set("players." + player.getName().toLowerCase() + ".rewarded", true);
			
			plugin.getConfig().options().copyDefaults(true);
			plugin.saveConfig();
			
			String joinMessage = (String) plugin.getConfig().get("playerJoinMessage");
			int timesVoted = (int) plugin.getConfig().get("players." + player.getName().toLowerCase() + ".voted");

			message.join(player, joinMessage, timesVoted);
		}
	}	
}

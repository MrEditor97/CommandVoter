package Me.MrEditor97.CommandVoter.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import Me.MrEditor97.CommandVoter.CommandVoter;
import Me.MrEditor97.CommandVoter.Messages.Message;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class VotifierListener implements Listener {
	
	CommandVoter plugin;
	
	public VotifierListener(CommandVoter instance) {
		plugin = instance;
	}
	
	//On Votifier Event
	@EventHandler(priority = EventPriority.NORMAL)
    public void onVotifierEvent(VotifierEvent event) {
		Message message = new Message(plugin);
        Vote vote = event.getVote();
        
        int timesVoted = plugin.getConfig().getInt("players." + vote.getUsername().toLowerCase() + ".voted");
        int timesVotedNew = timesVoted + 1;
        
        //Add to times voted
        plugin.getConfig().set("players." + vote.getUsername().toLowerCase() + ".voted", timesVotedNew);
        plugin.getConfig().set("players." + vote.getUsername().toLowerCase() + ".rewarded", false);
        
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        
        //Message player
        message.voted(vote.getUsername().toLowerCase(), plugin.getConfig().getString("playerBroadcastMessage"), timesVotedNew);        
    }
	
}

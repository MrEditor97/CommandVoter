package Me.MrEditor97.CommandVoter;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Me.MrEditor97.CommandVoter.Commands.CommandHandler;
import Me.MrEditor97.CommandVoter.Listeners.PlayerJoinListener;
import Me.MrEditor97.CommandVoter.Listeners.VotifierListener;
import Me.MrEditor97.CommandVoter.Metrics.Metrics;

public class CommandVoter extends JavaPlugin {
	
	//Set Logger
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		
		//Start CommandVoter
		log.info("[CommandVoter] CommandVoter enabled.");
		
		//Set FileConfiguration
		final FileConfiguration config = this.getConfig();
		
		//Start Plugin Metrics
		startMetrics();
		
		//Start PluginManager
		PluginManager pluginManager = getServer().getPluginManager();
		
		//Register Listeners
		pluginManager.registerEvents(new PlayerJoinListener(this), this);
		pluginManager.registerEvents(new VotifierListener(this), this);
		
		//Register Command Listeners
		getCommand("vote").setExecutor(new CommandHandler(this));
		getCommand("topvoters").setExecutor(new CommandHandler(this));
		getCommand("rewards").setExecutor(new CommandHandler(this));
		
		//Copy and Save the configuration file
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	public void onDisable() {
		//log.info("[CommandVoter] CommandVoter disabled!");
		//saveConfig();
	}
	
	//Start Mertics
	public void startMetrics() {
		//Check if Metrics have been enabled in the configuration file
		if (getConfig().getBoolean("enable-metrics") == true) {
			log.info("[CommandVoter] Metrics have been enabled!");
			try {
			    Metrics metrics = new Metrics(this);
			    metrics.start();
			} catch (IOException e) {
			    log.info("[CommandVoter] Could not enable Metrics!");
			    log.info("[CommandVoter] Please report this error: " + e);
			}
		} else {
			log.info("[CommandVoter] Metrics have been disabled!");
		}
	}
	
}

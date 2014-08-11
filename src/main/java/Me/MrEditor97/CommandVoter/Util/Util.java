package Me.MrEditor97.CommandVoter.Util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import Me.MrEditor97.CommandVoter.CommandVoter;

public class Util {
	
	CommandVoter plugin;
	
	public Util(CommandVoter instance) {
		plugin = instance;
	}

	//Replace Colour ALT Codes with actual colours
	public String colorize(String message) {
		return (ChatColor.translateAlternateColorCodes('&', message));
	}
	
	//Replace Player ALT Codes with Player name
	public String playerize(String message, Player player) {
		return (message.replaceAll("%p", player.getName()));
	}
	
	//Replace Player ALT Codes with times Voted
	public String voterize(String message, int voted) {
		return (message.replaceAll("%v", "" + voted + ""));
	}
	
	//Replace Player ALT Codes with Player name (String)
	public String stringPlayerize(String message, String player) {
		return (message.replaceAll("%p", player));
	}
	
	//Return sorted Map
	public <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		Comparator<K> valueComparator =  new Comparator<K>() {
		    public int compare(K k1, K k2) {
		        int compare = map.get(k2).compareTo(map.get(k1));
		        if (compare == 0) return 1;
		        else return compare;
		    }
		};
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}
	
	//Check if YAML Nodes Exists
	public boolean nodeExists(Configuration config, String nodePath) {
		String node = config.getString(nodePath);
		return (node != null);
	}
	
	//Convert string to int
	public int parseInt(String string) {
		return Integer.parseInt(string);
	}
}

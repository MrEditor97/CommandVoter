package Me.MrEditor97.CommandVoter.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Me.MrEditor97.CommandVoter.CommandVoter;

public class Inventory {
	
	CommandVoter plugin;
	
	public Inventory(CommandVoter instance) {
		plugin = instance;
	}
	
	//Check whether the Player has space in inventory
	public boolean hasSpace(Player player, int spaceNeeded) {
		int space = 0;
		
		//Loop through all ItemStacks
		for (ItemStack content : player.getInventory().getContents()) {
			if (content == null) {
				//If space in inventory add 1 to space
				space++;
			}
		}
		
		//If space smaller or equal to the space needed
		if (space >= spaceNeeded) {
			//Already enough space
			return true;
		} else {
			//Not enough space
			return false;
		}
	}
	
	//Add items to the Players inventory
	@SuppressWarnings("deprecation")
	public boolean addItems(Player player, int level) {
		Util util = new Util(plugin);
		
		//For each item in the string list
		for (String item : plugin.getConfig().getStringList("rewards." + level)) {
			String[] itemdata = item.split(":");
			int id, amount;
			
			try {
				id = util.parseInt(itemdata[0]);
				amount = util.parseInt(itemdata[1]);
				player.getPlayer().getInventory().addItem(new ItemStack(id, amount));
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		return true;
	}	
}

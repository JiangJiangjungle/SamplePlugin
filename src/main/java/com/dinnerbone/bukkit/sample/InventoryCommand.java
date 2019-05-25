package com.dinnerbone.bukkit.sample;

import com.dinnerbone.bukkit.sample.need.InventoryUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = InventoryUtil.createInventory(player, 27,new int[]{11,13,15});
            player.openInventory(inventory);
            return true;
        } else {
            return false;
        }
    }
}

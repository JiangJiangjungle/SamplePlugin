package com.dinnerbone.bukkit.sample;

import com.dinnerbone.bukkit.sample.need.InventoryUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<Integer> list = new ArrayList<>();
            list.add(11);
            list.add(13);
            list.add(15);
            Inventory inventory = InventoryUtil.createInventory(player, 27, "配件栏", list);
            player.openInventory(inventory);
            return true;
        } else {
            return false;
        }
    }
}

package com.dinnerbone.bukkit.sample;

import com.dinnerbone.bukkit.sample.need.GunInfo;
import com.dinnerbone.bukkit.sample.need.InfoConfig;
import com.dinnerbone.bukkit.sample.need.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {
    public static Inventory inventory = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack itemStack = new ItemStack(Material.IRON_BLOCK);
            ItemUtil.setGunInfo(itemStack, new InfoConfig(), new GunInfo(),10,50);
            player.getInventory().setItem(0, itemStack);
            //修改隐藏字段
            ItemUtil.setValue(itemStack, "key", "value", false);
            player.getInventory().setItem(0, itemStack);
            System.out.println(ItemUtil.getValue(itemStack, "key"));
            ItemUtil.setValue(itemStack, "key", "value2", false);
            player.getInventory().setItem(0, itemStack);
            System.out.println(ItemUtil.getValue(itemStack, "key"));
            System.out.println(ItemUtil.setValue(itemStack, ItemUtil.COLOR + ChatColor.WHITE.getChar() + "伤害", ItemUtil.COLOR + ChatColor.GOLD.getChar() + "很大", true));
            player.getInventory().setItem(0, itemStack);
            return true;
        } else {
            return false;
        }
    }
}

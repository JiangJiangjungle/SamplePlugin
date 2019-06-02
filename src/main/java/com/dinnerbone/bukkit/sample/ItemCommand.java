package com.dinnerbone.bukkit.sample;

import com.dinnerbone.bukkit.sample.need.*;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
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
            String choose = args[0];
            if ("1".equals(choose)) {
                ItemStack itemStack = new ItemStack(Material.IRON_BLOCK);
                ItemUtil.initGunInfo(itemStack, new InfoConfig(), new BaseGun(), new Buff(), 10, 50);
                player.getInventory().setItem(0, itemStack);
                //修改隐藏字段
                ItemUtil.setValue(itemStack, "key", "value");
                player.getInventory().setItem(0, itemStack);
                System.out.println("key: " + ItemUtil.getValue(itemStack, "key"));
                ItemUtil.setValue(itemStack, "key2", "lalal");
                ItemUtil.setValue(itemStack, "key", "value2");
                player.getInventory().setItem(0, itemStack);
                System.out.println("key: " + ItemUtil.getValue(itemStack, "key"));
                System.out.println("key2: " + ItemUtil.getValue(itemStack, "key2"));
                MessageUtil.sendMessage(player, "冲锋枪：50/100");

                ItemStack[] itemStacks = player.getInventory().getArmorContents();
                System.out.println(DamageUtil.getDamage(itemStacks));
            } else {
                ItemStack itemStack = player.getInventory().getItem(0);
                ItemUtil.updateVisiableInfo(itemStack,ItemUtil.COLOR + ChatColor.WHITE.getChar() + "伤害",
                        ItemUtil.getBar(8,2));
            }
            return true;
        } else {
            return false;
        }
    }
}

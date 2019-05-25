package com.dinnerbone.bukkit.sample;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TickThread extends Thread {
    Player player;
    ItemStack itemStack;

    public TickThread(Player player, ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
    }

    @Override
    public void run() {
        while (true) {
            try {
                itemStack.setAmount(itemStack.getAmount() + 1);
                System.out.println(itemStack.getAmount());
                player.getInventory().setItem(0, itemStack);
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

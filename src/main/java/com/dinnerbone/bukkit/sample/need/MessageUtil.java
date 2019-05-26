package com.dinnerbone.bukkit.sample.need;

import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendMessage(Player player, String msg) {
        player.sendTitle("", msg);
    }
}

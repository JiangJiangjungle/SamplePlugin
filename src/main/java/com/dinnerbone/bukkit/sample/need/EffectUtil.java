package com.dinnerbone.bukkit.sample.need;

import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class EffectUtil {

    public static void playSound(Player player) {
        //暂时只有这个声音。。
        player.playEffect(player.getLocation(), Effect.BOW_FIRE, 0);
    }

}

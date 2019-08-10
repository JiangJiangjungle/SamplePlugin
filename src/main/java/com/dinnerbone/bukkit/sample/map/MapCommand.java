package com.dinnerbone.bukkit.sample.map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int num = Integer.parseInt(args[0]);
        if (num==0) {
            MapUtil.giveMap((Player) sender);
        }
        return true;
    }
}

package com.dinnerbone.bukkit.sample.need;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryUtil {


    /**
     * 创建一个配件Inventory，只有固定的配件slot可选
     *
     * @return
     */
    public static Inventory createInventory(Player player, int size, String name,List<Integer> slots) {
        Inventory inventory = Bukkit.createInventory(player, size, name);
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (slots.contains(slot)) {
                continue;
            }
            inventory.setItem(slot, new ItemStack(Material.ANVIL));
        }
        return inventory;
    }
}

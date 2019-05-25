package com.dinnerbone.bukkit.sample.need;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil {

    static Inventory inventory = null;

    /**
     * 创建一个配件Inventory，只有固定的配件slot可选
     *
     * @return
     */
    public static Inventory createInventory(Player player, int size, int[] slots) {
        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, "配件栏");
            for (int slot = 0, count = 0; slot < inventory.getSize(); slot++) {
                if (count < slots.length && slot == slots[count]) {
                    count++;
                    continue;
                }
                inventory.setItem(slot, new ItemStack(Material.ANVIL));
            }
        }
        return inventory;
    }
}

package com.dinnerbone.bukkit.sample.need;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class InventoryEventListener implements Listener {
    @EventHandler
    public void onAccessory(InventoryClickEvent event) {
        //todo 用配置替换
        if ("配件栏".equals(event.getClickedInventory().getName())) {
            event.setCancelled(true);
            //配件栏禁止左击
            if (ClickType.LEFT == event.getClick() || Material.AIR == event.getCurrentItem().getType()) {
                return;
            }
            //todo 这里用slot配置替代
            List<Integer> slots = new ArrayList<>();
            slots.add(11);
            slots.add(13);
            slots.add(15);
            //禁止非设定的slot点击
            if (!slots.contains(event.getSlot())) {
                return;
            }
            //todo 卸载配件
        } else {
            //todo 点击物品栏，若是枪械就打开配件栏，若是配件就装载或替换现有装备（！同时更新枪械ItemStack的lore）
        }
    }

}

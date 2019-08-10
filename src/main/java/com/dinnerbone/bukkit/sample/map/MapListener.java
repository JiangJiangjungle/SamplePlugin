package com.dinnerbone.bukkit.sample.map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;

public class MapListener implements Listener {

    @EventHandler
    public void onMapInit(MapInitializeEvent event) {
        MapView mapView = event.getMap();
        MapViewHolder.mapView = mapView;
        List<MapRenderer> mapRendererList = mapView.getRenderers();
        for (MapRenderer renderer : mapRendererList) {
            mapView.removeRenderer(renderer);
        }
        mapView.addRenderer(new CursorMapRenderer());
    }

    @EventHandler
    public void onMapIteract(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItem(event.getNewSlot());
        if (itemStack != null &&
                (Material.MAP == itemStack.getType() || Material.EMPTY_MAP == itemStack.getType())) {
            player.getInventory().setItemInHand(MapUtil.refreshMap(player));
        }
    }
}

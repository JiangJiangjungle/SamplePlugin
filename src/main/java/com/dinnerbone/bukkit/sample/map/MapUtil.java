package com.dinnerbone.bukkit.sample.map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapRenderer;

import java.util.List;
import java.util.Random;

public class MapUtil {

    static Random random = new Random();

    public static void giveMap(Player player) {
        ItemStack itemStack = new ItemStack(Material.EMPTY_MAP, 1, (short) 0);
        player.getInventory().setItemInHand(itemStack);
    }

    public static void refreshMap() {
        MyMapRenderer myMapRenderer = new MyMapRenderer((byte) (4 + random.nextInt(128) * 52 / 256));
        List<MapRenderer> mapRendererList = MapViewHolder.mapView.getRenderers();
        for (MapRenderer mapRenderer : mapRendererList) {
            if (mapRenderer instanceof CursorMapRenderer) continue;
            MapViewHolder.mapView.removeRenderer(mapRenderer);
        }
        MapViewHolder.mapView.addRenderer(myMapRenderer);
    }
}

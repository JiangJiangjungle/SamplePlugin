package com.dinnerbone.bukkit.sample.map;

import net.minecraft.server.v1_8_R3.WorldMap;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;
import java.util.Random;

public class MapUtil {

    private static short mapID = 1;
    private static Random random = new Random();

    public static void giveMap(Player player) {
        ItemStack itemStack = new ItemStack(Material.EMPTY_MAP);
        player.getInventory().setItemInHand(itemStack);
    }

    public static ItemStack refreshMap(Player player) {
        mapID++;
        ItemStack mapItem = new ItemStack(Material.MAP, 1, mapID);
        WorldServer ws = ((CraftWorld) player.getWorld()).getHandle();
        String name = "map_" + mapID;
        WorldMap map = new WorldMap(name);
//        map.scale = 3;
//        map.a(ws.getWorldData().b(), ws.getWorldData().d(), map.scale);
//        map.map = (byte) ws.dimension;
        map.c();
        ws.getServer().getServer().worlds.get(0).a(name, map);
        MapInitializeEvent event = new MapInitializeEvent(map.mapView);
        Bukkit.getServer().getPluginManager().callEvent(event);
        //根据mapID获取MapView对象
        MapView mapView = Bukkit.getMap(mapID);
        List<MapRenderer> mapRendererList = mapView.getRenderers();
        for (MapRenderer mapRenderer : mapRendererList) {
            if (mapRenderer instanceof CursorMapRenderer) continue;
            MapViewHolder.mapView.removeRenderer(mapRenderer);
        }
        //替换新的MapRenderer
        MyMapRenderer myMapRenderer = new MyMapRenderer((byte) (4 + random.nextInt(128) * 52 / 256));
        MapViewHolder.mapView.addRenderer(myMapRenderer);
        return mapItem;
    }
}

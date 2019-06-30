package com.dinnerbone.bukkit.sample.map;

import org.bukkit.entity.Player;
import org.bukkit.map.*;

public class CursorMapRenderer extends MapRenderer {

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        MapCursorCollection cursorCollection = new MapCursorCollection();
        float yaw = player.getLocation().getYaw() >= 0 ? player.getLocation().getYaw() : 360 + player.getLocation().getYaw();
        int direction = (int) yaw * 16 / 360;
        MapCursor cursor = new MapCursor((byte) 10, (byte) 10, (byte) direction, (byte) 1, true);
        cursorCollection.addCursor(cursor);
        canvas.setCursors(cursorCollection);
    }
}

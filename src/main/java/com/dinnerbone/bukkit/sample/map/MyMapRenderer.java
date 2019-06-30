package com.dinnerbone.bukkit.sample.map;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

import java.time.LocalDateTime;

@Data
public class MyMapRenderer extends MapRenderer {
    private int tickCount = 0;
    volatile byte color;

    public MyMapRenderer(byte color) {
        this.color = color;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        for (int x = 0; x < 128; ++x) {
            for (int y = 0; y < 128; ++y) {
                canvas.setPixel(x, y, color);
            }
        }
        canvas.drawText(10, 10, MinecraftFont.Font, LocalDateTime.now().toString());
    }
}

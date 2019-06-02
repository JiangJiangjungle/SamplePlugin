package com.dinnerbone.bukkit.sample.need;

import com.dinnerbone.bukkit.sample.Ammo;
import com.google.gson.Gson;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 重要
 */
public class ItemUtil {
    private static final char SEPARATOR = ':';
    public static final String COLOR = "§";

    public static final String MAP_TAG = "VALUE_MAP";

    /**
     * 初始化枪械ItemStack的信息（尚未加隐藏信息）
     *
     * @param itemStack
     * @param config
     * @param baseGun
     * @param remainAmmo
     * @param magazine
     */
    public static void initGunInfo(ItemStack itemStack, InfoConfig config, BaseGun baseGun, Buff buff, int remainAmmo, int magazine) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(COLOR + ChatColor.GREEN.getChar() + baseGun.getName());
        List<String> lores = itemMeta.getLore();
        if (lores == null) {
            lores = new LinkedList<>();
        }
        lores.add(COLOR + ChatColor.GRAY.getChar() + baseGun.getCode());
        lores.add(COLOR + ChatColor.WHITE.getChar() + baseGun.getIntroduction() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + baseGun.getAmmoLabel());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getMagazineSize() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + baseGun.getMagazineSize());
        //伤害
        String bar = getBar(baseGun.getDamageInfo(), buff.getDamageInfo());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getDamage() + SEPARATOR
                + bar);
        //精度
        bar = getBar(baseGun.getAccuracyInfo(), buff.getAccuracyInfo());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getAccuracy() + SEPARATOR
                + bar);
        //射程
        bar = getBar(baseGun.getRangeInfo(), buff.getRangeInfo());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getRange() + SEPARATOR
                + bar);
        //提示信息
        List<String> tips = baseGun.getTips();
        for (String tip : tips) {
            lores.add(COLOR + ChatColor.WHITE.getChar() + tip);
        }
        //弹夹信息
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getAmmo() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + remainAmmo + "/" + magazine);
        //todo 增加隐藏信息
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
    }

    public static void initAccessoryInfo(ItemStack itemStack, Accessory accessory) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(COLOR + ChatColor.GREEN.getChar() + accessory.getName());
        List<String> lores = itemMeta.getLore();
        if (lores == null) {
            lores = new LinkedList<>();
        }
        //todo 隐藏信息
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
    }

    public static void initAmmoInfo(ItemStack itemStack, Ammo ammo) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(COLOR + ChatColor.GREEN.getChar() + ammo.getName());
        List<String> lores = itemMeta.getLore();
        if (lores == null) {
            lores = new LinkedList<>();
        }
        //todo 隐藏信息
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * 更新物品能力栏，只更新已存在的字段
     *
     * @param itemStack
     * @param key
     * @param value
     * @return
     */
    public static boolean updateVisiableInfo(ItemStack itemStack, String key, String value) {
        if (itemStack == null) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return false;
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new LinkedList<>();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return false;
        }
        boolean added = false;
        Iterator<String> iterator = lore.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            String text = iterator.next();
            boolean match = text.startsWith(key);
            if (!match) {
                index++;
                continue;
            }
            iterator.remove();
            text = key + SEPARATOR + value;
            lore.add(index, text);
            added = true;
            break;
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return added;
    }


    /**
     * Hides text in color codes
     */
    public static String hideText(String text) {
        StringBuilder output = new StringBuilder();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            output.append(ChatColor.COLOR_CHAR).append(c);
        }
        return output.toString();
    }

    /**
     * Reveals the text hidden in color codes
     */
    public static String revealText(String text) {
        if (text.isEmpty()) {
            return text;
        }
        char[] chars = text.toCharArray();
        char[] hexChars = new char[chars.length / 2];
        IntStream.range(0, chars.length)
                .filter(value -> (value & 1) == 1)
                .forEach(value -> hexChars[value / 2] = chars[value]);
        return new String(hexChars);
    }

    /**
     * 设置隐藏字段值(写在物品名称后面)
     *
     * @param itemStack
     * @param key
     * @param value
     * @return
     */
    public static boolean setValue(ItemStack itemStack, String key, String value) {
        if (itemStack == null) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return false;
        Map<String, Object> map = getMap(itemStack);
        map = map == null ? new HashMap<>(2) : map;
        map.put(key, value);
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        String hideText = hideText(jsonString);
        String name = itemMeta.getDisplayName();
        String prefix = hideText(MAP_TAG);
        int index = name.indexOf(prefix);
        itemMeta.setDisplayName((index == -1 ? name : name.substring(0, index)) + prefix + hideText);
        itemStack.setItemMeta(itemMeta);
        return true;
    }

    /**
     * 根据key查询隐藏字段
     *
     * @param itemStack
     * @param key
     * @return
     */
    public static String getValue(ItemStack itemStack, String key) {
        Map<String, Object> map = getMap(itemStack);
        if (map == null) return null;
        return (String) map.get(key);
    }

    /**
     * 根据隐藏的JSON格式字符串获取map
     *
     * @param itemStack
     * @return
     */
    private static Map<String, Object> getMap(ItemStack itemStack) {
        if (itemStack == null) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return null;
        String name = itemMeta.getDisplayName();
        String prefix = hideText(MAP_TAG);
        int index = name.indexOf(prefix);
        if (index != -1) {
            //得到隐藏前缀后的隐藏json字符串部分
            String subText = name.substring(index + prefix.length());
            String jsonString = revealText(subText);
            Gson gson = new Gson();
            return (Map<String, Object>) gson.fromJson(jsonString, Map.class);
        }
        return null;
    }

    /**
     * 得到能力栏
     *
     * @param number
     * @param add
     * @return
     */
    public static String getBar(double number, double add) {
        StringBuilder stringBuilder = new StringBuilder(4 + (int) number + (int) add);
        stringBuilder.append(COLOR).append(ChatColor.WHITE.getChar());
        for (int i = 0; i < number; i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append(COLOR).append(ChatColor.GREEN.getChar());
        for (int i = 0; i < add; i++) {
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }
}

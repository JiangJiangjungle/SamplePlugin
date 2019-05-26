package com.dinnerbone.bukkit.sample.need;

import com.dinnerbone.bukkit.sample.Ammo;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 重要
 */
public class ItemUtil {
    private static final char SEPARATOR = ':';
    public static final String COLOR = "§";

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
     * Hides text in color codes
     */
    public static String hideText(String text) {
        StringBuilder output = new StringBuilder();
        output.append(' ');
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
        IntStream.range(1, chars.length)
                .filter(value -> value % 2 == 0)
                .forEach(value -> hexChars[value / 2 - 1] = chars[value]);
        return new String(hexChars);
    }

    /**
     * 设置字段值
     *
     * @param itemStack
     * @param key
     * @param value
     * @param visiable
     * @return
     */
    public static boolean setValue(ItemStack itemStack, String key, String value, boolean visiable) {
        if (itemStack == null) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return false;
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new LinkedList<>();
        }
        Iterator<String> iterator = lore.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (visiable == str.startsWith(" ")) {
                i++;
                continue;
            }
            String text = visiable ? str : revealText(str);
            if (!text.startsWith(key)) {
                i++;
                continue;
            }
            iterator.remove();
            break;
        }
        lore.add(i, visiable ? key + SEPARATOR + value : hideText(key + SEPARATOR + value));
        itemMeta.setLore(lore);
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
        if (itemStack == null) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return null;
        List<String> lore = itemMeta.getLore();
        if (lore == null) return null;
        for (String str : lore) {
            if (!str.startsWith(" ")) continue;
            String text = revealText(str);
            if (!text.startsWith(key)) continue;
            return text.substring(text.indexOf(SEPARATOR) + 1);
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

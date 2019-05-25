package com.dinnerbone.bukkit.sample.need;

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

    public static void setItemInfo(ItemStack itemStack, InfoConfig config, Info info) {
        ItemMeta itemMeta = itemStack.getItemMeta();
//        ChatColor.COLOR_CHAR='§'
        itemMeta.setDisplayName(COLOR + ChatColor.GREEN.getChar() + info.getName());
        List<String> lores = itemMeta.getLore();
        if (lores == null) {
            lores = new LinkedList<>();
        }
        lores.add(COLOR + ChatColor.WHITE.getChar() + info.getIntroduction());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getMagazineSize() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + info.getMagazineSize());

        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getDamage() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + info.getDamage());

        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getAccuracy() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + info.getAccuracy());

        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getRange() + SEPARATOR
                + COLOR + ChatColor.GOLD.getChar() + info.getRange());

        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getTip1());
        lores.add(COLOR + ChatColor.WHITE.getChar() + config.getTip2());
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
        lore.add(hideText(key + SEPARATOR + value));
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

    public static String getBar(int number){
        return "";
    }
}

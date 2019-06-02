package com.dinnerbone.bukkit.sample.need;

import net.minecraft.server.v1_8_R3.ItemArmor;
import org.bukkit.Location;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class DamageUtil {
//    static ScriptEngineManager manager = new ScriptEngineManager();
//    static ScriptEngine engine = manager.getEngineByName("javascript");

    public static double getDamage(ItemStack[] itemStacks) {
        double armor = 0;
        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] == null) continue;
            switch (itemStacks[i].getType()) {
                case DIAMOND_HELMET:
                    armor += ItemArmor.EnumArmorMaterial.DIAMOND.b(0);
                    break;
                case DIAMOND_CHESTPLATE:
                    armor += ItemArmor.EnumArmorMaterial.DIAMOND.b(1);
                    break;
                case DIAMOND_LEGGINGS:
                    armor += ItemArmor.EnumArmorMaterial.DIAMOND.b(2);
                    break;
                case DIAMOND_BOOTS:
                    armor += ItemArmor.EnumArmorMaterial.DIAMOND.b(3);
                    break;
                case LEATHER_HELMET:
                    armor += ItemArmor.EnumArmorMaterial.LEATHER.b(0);
                    break;
                case LEATHER_CHESTPLATE:
                    armor += ItemArmor.EnumArmorMaterial.LEATHER.b(1);
                    break;
                case LEATHER_LEGGINGS:
                    armor += ItemArmor.EnumArmorMaterial.LEATHER.b(2);
                    break;
                case LEATHER_BOOTS:
                    armor += ItemArmor.EnumArmorMaterial.LEATHER.b(3);
                    break;
                case GOLD_HELMET:
                    armor += ItemArmor.EnumArmorMaterial.GOLD.b(0);
                    break;
                case GOLD_CHESTPLATE:
                    armor += ItemArmor.EnumArmorMaterial.GOLD.b(1);
                    break;
                case GOLD_LEGGINGS:
                    armor += ItemArmor.EnumArmorMaterial.GOLD.b(2);
                    break;
                case GOLD_BOOTS:
                    armor += ItemArmor.EnumArmorMaterial.GOLD.b(3);
                    break;
                case IRON_HELMET:
                    armor += ItemArmor.EnumArmorMaterial.IRON.b(0);
                    break;
                case IRON_CHESTPLATE:
                    armor += ItemArmor.EnumArmorMaterial.IRON.b(1);
                    break;
                case IRON_LEGGINGS:
                    armor += ItemArmor.EnumArmorMaterial.IRON.b(2);
                    break;
                case IRON_BOOTS:
                    armor += ItemArmor.EnumArmorMaterial.IRON.b(3);
                    break;
                case CHAINMAIL_HELMET:
                    armor += ItemArmor.EnumArmorMaterial.CHAIN.b(0);
                    break;
                case CHAINMAIL_CHESTPLATE:
                    armor += ItemArmor.EnumArmorMaterial.CHAIN.b(1);
                    break;
                case CHAINMAIL_LEGGINGS:
                    armor += ItemArmor.EnumArmorMaterial.CHAIN.b(2);
                    break;
                case CHAINMAIL_BOOTS:
                    armor += ItemArmor.EnumArmorMaterial.CHAIN.b(3);
                    break;
                default:
                    break;
            }
        }
        return armor;
    }
}
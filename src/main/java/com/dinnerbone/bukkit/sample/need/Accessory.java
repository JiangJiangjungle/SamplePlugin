package com.dinnerbone.bukkit.sample.need;

import lombok.Data;

@Data
public class Accessory {
    String name;
    String code;

    //用于lore显示的属性值
    Integer damageInfo=3;
    Integer rangeInfo=4;
    Integer accuracyInfo=5;
}
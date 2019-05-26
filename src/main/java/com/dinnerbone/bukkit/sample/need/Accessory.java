package com.dinnerbone.bukkit.sample.need;

import lombok.Data;

@Data
public class Accessory {
    String name;

    //用于lore显示的属性值
    Integer damageInfo;
    Integer rangeInfo;
    Integer accuracyInfo;
}

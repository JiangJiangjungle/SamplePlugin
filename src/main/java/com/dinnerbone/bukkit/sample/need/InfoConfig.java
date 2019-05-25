package com.dinnerbone.bukkit.sample.need;

import lombok.Data;

@Data
public class InfoConfig {
    private String introduction = "";
    private String magazineSize = "弹夹容量";
    private String damage = "伤害";
    private String accuracy = "精度";
    private String range = "射程";
    private String tip1 = "枪需放在一/二号物品栏才可使用";
    private String tip2 = "左键装弹，右键射击";
}

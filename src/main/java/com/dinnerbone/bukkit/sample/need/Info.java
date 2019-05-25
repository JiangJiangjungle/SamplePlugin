package com.dinnerbone.bukkit.sample.need;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private String name = "冲锋枪";
    private String introduction = "这是冲锋枪的简介，可配置子弹：";
    private String magazineSize = "25";
    private String damage = "3";
    private String accuracy = "4";
    private String range = "5";
}

package com.dinnerbone.bukkit.sample.need;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GunInfo {
    private String name = "冲锋枪";
    private String code ="C418-cat";
    private String introduction = "这是冲锋枪的简介，可配置子弹";
    private String ammoLabel = "冲锋枪子弹（0.45）口径";
    private Integer magazineSize = 25;
    private Integer damage = 3;
    private Integer accuracy = 4;
    private Integer range = 5;
    private List<String> tips = new ArrayList<>();

    {
        tips.add("枪需放在一/二号物品栏才可使用");
        tips.add("左键射击，射击触发装弹");
    }
}

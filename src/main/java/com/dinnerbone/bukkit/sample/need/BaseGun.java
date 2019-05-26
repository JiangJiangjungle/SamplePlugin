package com.dinnerbone.bukkit.sample.need;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseGun {
    private String name = "冲锋枪";
    private String code = "C418-cat";
    private String introduction = "这是冲锋枪的简介，可配置子弹";
    private String ammoLabel = "冲锋枪子弹（0.45）口径";
    private Integer magazineSize = 25;
    private List<String> tips = new ArrayList<>();

    {
        tips.add("左键射击，射击触发装弹");
    }

    //用于lore显示的属性值
    private Integer damageInfo = 3;
    private Integer accuracyInfo = 3;
    private Integer rangeInfo = 5;
}

package com.qcl.pattern.skill.impl;

import com.qcl.pattern.skill.ISkill;

/**
 * 召唤师的技能(疾跑)
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class JiPao implements ISkill {
    @Override
    public void useSkill() {
        System.out.println("疾跑：75秒CD，增加30%移动速度持续10秒");
    }
}

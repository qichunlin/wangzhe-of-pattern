package com.qcl.pattern.skill.impl;

import com.qcl.pattern.skill.ISkill;

/**
 * 召唤师的技能(狂暴)
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class KuangBao implements ISkill {
    @Override
    public void useSkill() {
        System.out.println("狂暴：60秒CD，增加攻击速度60%持续5秒");
    }
}

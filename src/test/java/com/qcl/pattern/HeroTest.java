package com.qcl.pattern;

import com.qcl.pattern.domain.Hero;
import com.qcl.pattern.domain.HouYi;
import com.qcl.pattern.skill.impl.JiPao;

/**
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class HeroTest {

    public static void main(String[] args) {
        //1.选择英雄
        Hero hero = new HouYi();
        //2.英雄自我介绍
        System.out.println("英雄:" + hero.getHeroName() + ",自我介绍!");
        //3.玩家根据队伍情况,设置英雄召唤师的技能
        hero.setiSkill(new JiPao());//面向接口编程体现
        //4.游戏开始
        System.out.println("游戏开始,请做好准备!!!");
        //5.使用英雄的普通攻击
        hero.normalAttack();
        //6.使用英雄的技能攻击
        hero.skill();

    }
}

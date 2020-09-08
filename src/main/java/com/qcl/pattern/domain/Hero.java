package com.qcl.pattern.domain;

import com.qcl.pattern.skill.ISkill;

/**
 * 英雄类(抽象类)
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public abstract class Hero {
    /**
     * 英雄的名称
     */
    private String heroName;

    /**
     * 加入一个新的成员
     */
    private Integer heroHurt;

    /**
     * 英雄的召唤师技能的接口(组合关系,需要使用时,需要传入一个具体的技能对象)
     */
    private ISkill iSkill;

    /**
     * 英雄的自我介绍的方法
     */
    public abstract void display();

    /**
     * 英雄的普通攻击
     */
    public abstract void normalAttack();
    /**
     * 英雄的召唤师的技能使用方法
     */
    public void skill(){
        iSkill.useSkill();
    }


    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setiSkill(ISkill iSkill) {
        this.iSkill = iSkill;
    }

    public Integer getHeroHurt() {
        return heroHurt;
    }

    public void setHeroHurt(Integer heroHurt) {
        this.heroHurt = heroHurt;
    }
}

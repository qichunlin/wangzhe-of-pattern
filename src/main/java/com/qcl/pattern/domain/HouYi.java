package com.qcl.pattern.domain;

/**
 * 英雄后裔类
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class HouYi extends Hero{

    /**
     * 构造方法
     */
    public HouYi(){
        super.setHeroName("后裔");
    }

    /**
     * 后裔英雄的自我介绍
     */
    @Override
    public void display() {
        System.out.println("太阳都被我设熄了火");
    }

    /**
     * 后裔英雄的普通攻击
     */
    @Override
    public void normalAttack() {
        System.out.println("xiuxiuxiu,,,,被动：迟缓之箭");
    }
}

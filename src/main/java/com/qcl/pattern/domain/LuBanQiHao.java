package com.qcl.pattern.domain;

/**
 * 英雄鲁班七号类
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class LuBanQiHao extends Hero{

    /**
     * 构造方法
     */
    public LuBanQiHao(){
        super.setHeroName("鲁班七号");
    }

    /**
     * 鲁班七号英雄的自我介绍
     */
    @Override
    public void display() {
        System.out.println("dididi鲁班七号");
    }

    /**
     * 鲁班七号英雄的普通攻击
     */
    @Override
    public void normalAttack() {
        System.out.println("被动,,,,：远程炮弹");
    }
}

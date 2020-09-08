package com.qcl.pattern.domain;

/**
 * 基地(单例类)
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class Base {

    /**
     * 构造方法私有
     */
    private Base() {
    }

    /**
     * 创建一个私有的静态的基地对象的引用
     */
    private static Base base = null;

    /**
     * 提供一个公共的静态的基地对象或基地对象的方法
     */
    public static Base getBase() {
        if (base == null) {
            base = new Base();
        }
        return base;
    }

    /**
     * 基地的生命值
     */
    private int life = 999;

    /**
     * 基地的摧毁状态
     */
    private boolean destory = false;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isDestory() {
        return destory;
    }

    public void setDestory(boolean destory) {
        this.destory = destory;
    }
}

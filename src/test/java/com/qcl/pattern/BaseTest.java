package com.qcl.pattern;

import com.qcl.pattern.domain.Base;
import com.qcl.pattern.domain.Hero;
import com.qcl.pattern.domain.HouYi;
import com.qcl.pattern.domain.LuBanQiHao;
import com.qcl.pattern.skill.impl.JiPao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/7
 */
public class BaseTest {

    public static void main(String[] args) {
        System.out.println("所有英雄集合,攻击基地");
        //模拟两个英雄,攻击基地
        //第一个英雄
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //1.创建英雄,设置英雄单次攻击伤害值
                Hero hero = new HouYi();
                //攻击力100
                hero.setHeroHurt(100);
                //2.获取基地,英雄攻击基地
                Base base = Base.getBase();
                destory(hero, base);
            }
        });
        thread.start();

        //第二个英雄
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //1.创建英雄,设置英雄单次攻击伤害值
                Hero hero = new LuBanQiHao();
                //攻击力60
                hero.setHeroHurt(60);
                //2.获取基地,英雄攻击基地
                Base base = Base.getBase();
                destory(hero, base);
            }
        });
        thread2.start();

        //关闭线程
       /* thread.interrupt();
        thread2.interrupt();*/
    }

    /**
     * 英雄摧毁基地的方法
     *
     * @param hero
     * @param base
     */
    private static void destory(Hero hero, Base base) {
        //1.显示哪里英雄在攻击基地，英雄的伤害值是多少
        System.out.println("英雄:" + hero.getHeroName() + "伤害值:" + hero.getHeroHurt());
        //2.攻击基地
        int life = base.getLife();

        //判断基地生命值>0,若生命值>0,英雄持续攻击
        while (life > 0) {
            synchronized (base) {
                //2.1 判断当前基地的状态是否已经摧毁
                if (!base.isDestory()) {
                    //若没有摧毁
                    //攻击基地,基地的剩余生命值减少
                    //基地的剩余生命值 = 基地当前的生命值 - 英雄的攻击力
                    base.setLife(base.getLife() - hero.getHeroHurt());

                    //模拟攻击基地的耗时操作
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                    if (base.getLife() > 0) {
                        //判断基地 生命值>0,英雄攻击基地
                        System.out.println("英雄【" + hero.getHeroName() + "】正在攻击基地,基地剩余生命值" + base.getLife());
                    } else {
                        //判断基地生命值<=0，说明基地已被摧毁，更新基地的状态为摧毁，游戏胜利
                        base.setDestory(true);
                        System.out.println("基地已被【" + hero.getHeroName() + "】摧毁,游戏胜利");
                    }
                }
            }
        }
    }
}

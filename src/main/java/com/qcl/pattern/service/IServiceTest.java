package com.qcl.pattern.service;


/**
 * 编程式事务
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/8
 */
public interface IServiceTest {

    /**
     * 声明式事务
     *
     * @return
     */
    public String method();

    /**
     * 编程式事务
     *
     * @return
     */
    public String method2();
}

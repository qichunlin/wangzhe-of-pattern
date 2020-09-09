package com.qcl.pattern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 编程式事务
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/8
 */
@Service
public class ServiceTest implements IServiceTest{


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String method() {
        //通过方法上面注解@Transactional 它是对方法内的代码进行事务管理并且占用数据库连接即使你的业务代码没有进行数据库相关操作
        return null;
    }


    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String method2() {

        //执行部分业务处理
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                //业务处理
                //将数据保存到我们自己的业务数据库
                return null;
            }
        });
        //调用远程接口不需要占用数据库连接是用编程式事务能够在做到对代码块进行事务管理

        //再次进行业务处理
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                //业务处理
                //更新数据
                return null;
            }
        });
        return null;
    }
}

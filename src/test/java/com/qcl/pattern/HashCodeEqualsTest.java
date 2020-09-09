package com.qcl.pattern;

import com.qcl.pattern.equalandhashcode.Student;

/**
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/9
 */
public class HashCodeEqualsTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("legend");
        student.setAge("12");
        student.setSex("男");

        Student student2 = new Student();
        student2.setName("legend");
        student2.setAge("12");
        student2.setSex("男");
        //false
        System.out.println(student == student2);
        //true
        System.out.println(student.equals(student2));
    }
}

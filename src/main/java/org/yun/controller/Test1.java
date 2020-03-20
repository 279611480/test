package org.yun.controller;

import org.junit.Test;

import java.util.Date;

/**
 * @ClassName Test1
 * @Author 芸
 * @Date 2020/2/27 15:57
 * @Description ....杂
 **/
public class Test1 {

    @Test
    public void test1() throws InterruptedException {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                    System.out.println("?????");
                    System.out.println("start：" + new Date());
                    System.out.println("end：" + new Date());
                    System.out.println("?????2222");
            }
        };

        new Thread(runnable1).start();
        Thread.sleep(1000);//1S
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                    System.out.println("start：" + new Date());
                    System.out.println("end：" + new Date());
            }
        };

        new Thread(runnable2).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("略略略");
            }
        }).start();

    }

    @Test
    public void test2(){
        System.out.println(2<<3);//2的3次方  ---16   不可过大   eg:2的30次方就会溢出，造成结果不准确
    }
    @Test
    public void test3(){
        System.out.println(Test1.class.getPackage());

        System.out.println(Test1.class.getPackage().getName());
    }


}

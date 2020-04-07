package org.yun.controller;

import cn.hutool.Hutool;

/**
 * @ClassName Cat
 * @Author 芸
 * @Date 2020/3/2 9:44
 * @Description TODO
 **/
public class Cat extends Animal {
    @Override
    public void say() {
        System.out.println("Cats说话了");
    }

    public static void main(String[] args) {
        Animal a = new Animal();
        Animal b = new Cat();
        a.say();
        b.say();
    }

}

package org.yun.controller;

import org.junit.Test;
import org.yun.entity.Student;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName Test3
 * @Author 芸
 * @Date 2020/2/28 16:45
 * @Description TODO
 **/
public class Test3 {
    @Test
    public void test() {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        this.sortUsingJava7(names1);
        System.out.println("7" + names1);
        this.sortUsingJava8(names2);
        System.out.println("8" + names2);
    }


    @Test
    public void test2() {
        ArrayList<Student> lists = new ArrayList<Student>();
//        lists.add(new Student(1,"曹操","22",99.50));
        //一次性添加多条数据
        Collections.addAll(lists, new Student(1, "曹操", "22", 99.50)
                , new Student(2, "郭嘉", "22", 99.50)
                , new Student(11, "许褚", "22", 90.50)
                , new Student(9, "典韦", "22", 90.50)
                , new Student(5, "荀彧", "22", 90.50)

        );
        Stream<Student> stream = lists.stream();//顺序流
        Stream<Student> studentStream = lists.parallelStream();//并行流
    }


    //Java7排序
    private void sortUsingJava7(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    //java8排序
    private void sortUsingJava8(List<String> list) {
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
    }
}

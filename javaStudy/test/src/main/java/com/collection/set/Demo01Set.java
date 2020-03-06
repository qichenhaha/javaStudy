package com.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Create by heidan on 2020/2/3 16:07
 */

/**
 *      java.util.Set 接口 extends Collention接口
 *      set接口特点
 *          1.不允许重复的元素
 *          2.没有索引
 *       java.util.HashSet 集合 implements Set接口
 *       HashSet特点
 *          1.不允许存储重复的元素
 *          2.没有索引
 *          3.是一个无序的集合
 *          4.底层是哈希表结构
 */
public class Demo01Set extends Object{
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(8);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);

        // 使用迭代器遍历
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
        }
        System.out.println("=================================");
        // 使用增强for循环遍历
        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}

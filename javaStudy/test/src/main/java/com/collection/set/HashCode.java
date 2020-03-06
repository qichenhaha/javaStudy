package com.collection.set;

/**
 * Create by heidan on 2020/2/3 16:28
 */

/**
 * 哈希值：是一个十进制的整数，有系统随机给出（就是对象的地址值，是一个逻辑地址，是模拟出来得到地址，不是数据的实际物理地址）
 */
public class HashCode {

    public static void main(String[] args) {
      Cats cats = new Cats();
      Cats cats1 = new Cats();
      System.out.println(cats.hashCode()); // 1836019240
      System.out.println(cats1.hashCode()); // 325040804
      System.out.println(cats.equals(cats1)); // false
      System.out.println("李四".hashCode()); // 842061
      System.out.println("哈希".hashCode()); // 696900
    }

}

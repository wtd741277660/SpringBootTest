package com.springboot.test.SpringBootTest.jvm.gc;

/**
 * 实现Minot GC的触发过程
 * jvm参数设置为：
 * -Xms30m -Xmx30m -Xmn10m -XX:+PrintGCDetails -XX:+PrintHeapAtGC
 * 堆总大小为30m，新生代为10m，打印GC信息，在GC前后打印内存信息
 * eden、from、to区域在新生代的比例为 8:1:1
 */
public class MinorGCTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] agrs) {
        testMinorGC();
    }

    private static void testMinorGC() {
        /* eden space为8M，from/to space各为1M */
        LargeObject largeOb1 = new LargeObject(_1M * 1 / 2, "largeOb1");
        LargeObject largeOb2 = new LargeObject(_1M * 1, "largeOb2");
        LargeObject largeOb3 = new LargeObject(_1M * 2, "largeOb3");
        largeOb3 = null;
        LargeObject largeOb4 = new LargeObject(_1M * 3, "largeOb4");
        LargeObject largeOb5 = new LargeObject(_1M * 2, "largeOb5");
    }

    static class LargeObject {
        private byte[] data;
        private String name;

        public LargeObject(int size, String name) {
            data = new byte[size];
            this.name = name;
            System.out.println("Over Constructing LargeObject " + name + System.lineSeparator());
        }

        public String getName() {
            return name;
        }
    }
}

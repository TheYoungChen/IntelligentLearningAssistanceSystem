package com.cqust;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main Message"); // 设置主线程的 ThreadLocal 变量

        System.out.println("主线程 " + Thread.currentThread().getName() + " 的变量：" + threadLocal.get());

        // 创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Child Message");
                System.out.println("子线程 " + Thread.currentThread().getName() + " 的变量：" + threadLocal.get());
            }
        }).start();

        threadLocal.remove();

        System.out.println("主线程 " + Thread.currentThread().getName() + " 的变量：" + threadLocal.get());
    }
}

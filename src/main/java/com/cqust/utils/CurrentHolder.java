package com.cqust.utils;

public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    // 存储当前员工的id
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    // 获取当前员工的id
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    // 删除当前线程存储的id
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}

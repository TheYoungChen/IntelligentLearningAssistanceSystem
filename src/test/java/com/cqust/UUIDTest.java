package com.cqust;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void testUUID() {
        for (int i = 0; i < 100; i++) {
            System.out.println("本次随机的UUID = " + UUID.randomUUID().toString());
        }
    }
}

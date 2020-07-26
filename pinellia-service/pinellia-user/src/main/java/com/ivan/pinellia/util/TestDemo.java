package com.ivan.pinellia.util;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;

/**
 * <p></p>
 *
 * @author ivan
 * @className TestDemo
 * @since 2020/7/22 22:58
 */
public class TestDemo {

    public static void main(String[] args) throws Exception {
        String password = "chenyifan";

        String encodePassword = CryptoUtils.encrypt(password);
        System.out.println(encodePassword);
    }
}

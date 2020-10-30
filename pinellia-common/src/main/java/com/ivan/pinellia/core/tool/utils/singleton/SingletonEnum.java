package com.ivan.pinellia.core.tool.utils.singleton;

/**
 * <p></p>
 *
 * @author chenyf
 * @className SingletonEnum
 * @since 2020/10/14 16:01
 */
public enum SingletonEnum {

    INSTANCE
    ;

    SingletonEnum() {
    }

    public static SingletonEnum getInstance() {
        return INSTANCE;
    }
}

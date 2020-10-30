package com.ivan.pinellia.core.tool.utils.singleton;

import cn.hutool.core.util.ObjectUtil;

/**
 * <p></p>
 *
 * @author chenyf
 * @className LazySingleton
 * @since 2020/10/14 10:39
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {};

    public static LazySingleton getInstance() {

        if (ObjectUtil.isNull(singleton)) {
            singleton = new LazySingleton();
            return singleton;
        }
        return singleton;
    }

}

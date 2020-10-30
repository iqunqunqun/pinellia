package com.ivan.pinellia.core.tool.utils.singleton;

/**
 * <p></p>
 *
 * @author chenyf
 * @className HungrySingleton
 * @since 2020/10/14 10:28
 */
public class HungrySingleton {

    private static final HungrySingleton SINGLETON = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return SINGLETON;
    }

}

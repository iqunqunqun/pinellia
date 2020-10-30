package com.ivan.pinellia.core.tool.utils.singleton;

/**
 * <p></p>
 *
 * @author chenyf
 * @className DCLSingleton
 * @since 2020/10/14 10:42
 */
public class DCLSingleton {



    private static volatile DCLSingleton singleton = null;

    private DCLSingleton() {}

    public static DCLSingleton getInstance() {

        if (singleton == null) {

            synchronized (DCLSingleton.class) {
                if (singleton == null) {
                    singleton = new DCLSingleton();
                }
            }

        }
        return singleton;

    }

}

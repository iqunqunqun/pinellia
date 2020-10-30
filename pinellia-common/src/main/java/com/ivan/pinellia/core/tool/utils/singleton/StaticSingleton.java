package com.ivan.pinellia.core.tool.utils.singleton;

/**
 * <p></p>
 *
 * @author chenyf
 * @className StaticSingleton
 * @since 2020/10/14 16:05
 */
public class StaticSingleton {

    private StaticSingleton() {}

    public static StaticSingleton getInstance() {
        return StaticHolderSingleton.INSTANCE;
    }

    private static class StaticHolderSingleton {
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }

}

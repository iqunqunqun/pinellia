package com.ivan.pinellia.core.tool.utils.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author chenyf
 * @className Util
 * @since 2020/10/28 15:48
 */
public class Util {

    public static Map<String, Object> map;

    public static ConcurrentHashMap<String, Object> concurrentHashMap;

    static {
        map = new HashMap<String, Object>(16);
        concurrentHashMap = new ConcurrentHashMap<>(16);
    }

    public static Map<String, Object> getMap() {
        return map;
    }

    public static ConcurrentHashMap<String, Object> getConMap() {
        return concurrentHashMap;
    }

}

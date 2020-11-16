package com.ivan.pinellia.provider;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author ivan
 * @className SkipUrlProvider
 * @since 2020/11/14 11:40
 */

@Component
public class SkipUrlProvider {

    public static String TARGET = "/**";

    public static String REPLACEMENT = "";

    public static List<String> skipUrlList = new ArrayList<>();

    static {
        skipUrlList.add("/auth/**");
        skipUrlList.add("/oauth/**");
    }

    public List<String> getSkipUrlList() {
        return skipUrlList;
    }

    public static void main(String[] args) {
        String[] strings = skipUrlList.toArray(new String[0]);
        for (String string : strings) {
            System.out.println("string = " + string);
        }
    }
}

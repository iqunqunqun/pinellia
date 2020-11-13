package com.ivan.pinellia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author chenyf
 * @className TestController
 * @since 2020/11/11 17:47
 */

@RestController
@RequestMapping("/aaa")
public class TestController {

    @GetMapping("/normal")
    public String normal( ) {
        return "normal permission test success !!!";
    }

}

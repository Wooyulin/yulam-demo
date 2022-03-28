package cn.yulam.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 5yl
 * date: 2022/3/28
 */
@RestController
public class TestController {

    @RequestMapping("/servertest")
    public String serverTest() {
        return "i am server";
    }
}

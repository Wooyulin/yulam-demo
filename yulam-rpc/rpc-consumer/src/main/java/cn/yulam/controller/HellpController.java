package cn.yulam.controller;

import cn.yulam.anno.RpcConsumer;
import cn.yulam.api.service.HelloRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 5yl
 * date: 2022/3/28
 */
@RestController
@RequestMapping("/hello-rpc")
public class HellpController {

    @RpcConsumer(providerName = "provider")
    private HelloRpcService helloRpcService;

    @GetMapping("/hello")
    public String hello() {
        return helloRpcService.sayHello();
    }
}

package cn.yulam.provider.service.impl;

import cn.yulam.anno.RpcService;
import cn.yulam.api.service.HelloRpcService;

/**
 * @author 5yl
 * date: 2022/3/28
 */
@RpcService(HelloRpcService.class)
public class HelloRpcServiceImpl implements HelloRpcService {

    @Override
    public String sayHello() {
        return "server answer";
    }
}

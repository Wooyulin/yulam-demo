package cn.yulam.mockjito;

import java.util.Random;

public class HttpService {

    public int queryStatus() {
        // 发起网络请求，提取返回结果
        // 这里用随机数模拟结果
        return new Random().nextInt(2);
    }
}

package cn.yulam.ttl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TT {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        System.out.println(map.size());

        map.put(":", "s");
        System.out.println(map.size());

    }
}

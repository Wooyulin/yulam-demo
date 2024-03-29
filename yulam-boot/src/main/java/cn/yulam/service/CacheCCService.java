package cn.yulam.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheCCService {

    @Cacheable(cacheNames = "sss")
    public Integer getCount() {
        System.out.println("进行计算");
        return 1000;
    }

    @CachePut(cacheNames = "sss")
    public void putCount() {
        System.out.println("进行计算");
    }
}

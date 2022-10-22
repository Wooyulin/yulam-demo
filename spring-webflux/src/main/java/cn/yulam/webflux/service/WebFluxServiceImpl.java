package cn.yulam.webflux.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class WebFluxServiceImpl implements WebFluxService{

    @Override
    public String helloWebFlux(String id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return "Hello Webflux" + id;
    }
}

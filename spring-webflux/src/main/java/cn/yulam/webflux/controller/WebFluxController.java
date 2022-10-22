package cn.yulam.webflux.controller;

import cn.yulam.webflux.service.WebFluxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
public class WebFluxController {

    private static final Logger log =  LoggerFactory.getLogger(WebFluxController.class);
    @Resource
    private WebFluxService webFluxService;

    /**
     * 方式一
     * @param id
     * @return
     */
    @GetMapping("/hello1")
    public Mono<String> helloWebFlux(@RequestParam("id") String id){
        log.info("webflux start..");
        Mono<String> result = Mono.fromSupplier(()-> webFluxService.helloWebFlux(id));
        log.info("webflux end..");
        return result;
    }

    /**
     * 方式二
     * @param id
     * @return
     */
    @GetMapping("/hello2")
    public Mono<String> helloWebFlux2(@RequestParam("id") String id){
        return Mono.just("hello")
                .publishOn(Schedulers.elastic())
                .map(s -> webFluxService.helloWebFlux(id));
    }

    /**
     * MVC方式
     * @param id
     * @return
     */
    @GetMapping("/hello3")
    public String helloWebFlux3(@RequestParam("id") String id){
        log.info("mvc start..");
        String result = webFluxService.helloWebFlux(id);
        log.info("mvc end..");
        return result;
    }

    /**
     * Flux : 返回0-n个元素
     * 注：需要指定MediaType
     * @return
     */
    @GetMapping(value = "/hello4", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }
}

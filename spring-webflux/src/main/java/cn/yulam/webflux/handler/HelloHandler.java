package cn.yulam.webflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {

    public Mono<ServerResponse> greeting(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello World");
    }
}


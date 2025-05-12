package org.wenzhuo4657.test.gateway.gateway.repository;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.wenzhuo4657.test.gateway.gateway.bean.MyRouteDefinition;
import org.wenzhuo4657.test.gateway.gateway.support.impl.RouteHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class RedisRouteDefinitionRepository  implements RouteDefinitionRepository {


    public   static RouteHolder routeHolder;   //gateway专用

    public RedisRouteDefinitionRepository(RouteHolder routeHolder) {
            this.routeHolder=routeHolder;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeHolder.getMap().values());


    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}

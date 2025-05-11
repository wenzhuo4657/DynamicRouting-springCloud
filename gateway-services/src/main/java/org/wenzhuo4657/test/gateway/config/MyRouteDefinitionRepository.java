package org.wenzhuo4657.test.gateway.config;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wenzhuo4657.test.gateway.bean.MyRouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;


@Component
public class MyRouteDefinitionRepository implements RouteDefinitionRepository {


    @Autowired
    private RedissonClient redissonClient;

    String key="route_Map";


//    启用动态刷新时，会自动调用这个方法，启动、刷新，
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        System.out.println("开始刷新路由事件，-----");

        RMap<String, MyRouteDefinition> map = redissonClient.getMap(key);
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        for (MyRouteDefinition myRouteDefinition : map.values()){
            RouteDefinition routeDefinition = new RouteDefinition();

            routeDefinition.setId(myRouteDefinition.route_name);
            routeDefinition.setUri(URI.create(myRouteDefinition.route_uri));


            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setName(myRouteDefinition.route_predicate.name);
            predicateDefinition.setArgs(myRouteDefinition.route_predicate.args);
            routeDefinition.setPredicates(List.of(predicateDefinition));

            FilterDefinition filterDefinition=new FilterDefinition();
            filterDefinition.setName(myRouteDefinition.route_filter.name);
            filterDefinition.setArgs(myRouteDefinition.route_filter.args);
            routeDefinition.setFilters(List.of(filterDefinition));
            routeDefinitions.add(routeDefinition);
        }
        System.out.println("刷新完成！");
        return Flux.fromIterable(routeDefinitions);
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

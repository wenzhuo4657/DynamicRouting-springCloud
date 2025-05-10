package org.wenzhuo4657.test.gateway.autoConfigure;


import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wenzhuo4657.test.gateway.repository.RedisRouteDefinitionRepository;
import org.wenzhuo4657.test.gateway.support.impl.RouteHolder;

@Configuration
@ConditionalOnClass(RouteDefinitionRepository.class)//这里假定，只要存在该网关仓库的接口类，就认为当前模块是网关模块
public class GatewayAutoConfigure {



    @Bean("GatewayRouteHolder")
    public RouteHolder routeHolder(RedissonClient redissonClient){
        return new RouteHolder(redissonClient);
    }

    @Bean
    public RedisRouteDefinitionRepository redisRouteDefinitionRepository(RedissonClient redissonClient,RouteHolder routeHolder){
        return  new RedisRouteDefinitionRepository(routeHolder);
    }

}

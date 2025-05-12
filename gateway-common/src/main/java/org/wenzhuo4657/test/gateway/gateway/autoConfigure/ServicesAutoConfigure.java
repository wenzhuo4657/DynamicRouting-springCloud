package org.wenzhuo4657.test.gateway.gateway.autoConfigure;


import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.wenzhuo4657.test.gateway.gateway.support.impl.RouteHolder;

@Order(2)
@Configuration
//通过配置启用服务模块的配置，默认关闭
@ConditionalOnProperty(prefix = "gateway.services", name = "enable", havingValue = "true", matchIfMissing = false)
public class ServicesAutoConfigure {



    @Bean("GatewayRouteHolder")
    public RouteHolder routeHolder(RedissonClient redissonClient){
        return new RouteHolder(redissonClient);
    }


}

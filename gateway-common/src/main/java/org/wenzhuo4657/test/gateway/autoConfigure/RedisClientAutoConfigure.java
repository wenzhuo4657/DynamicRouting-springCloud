package org.wenzhuo4657.test.gateway.autoConfigure;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.wenzhuo4657.test.gateway.config.RedisClientConfigProperties;
import org.wenzhuo4657.test.gateway.event.ServicesLoginEvent;


/**
 * redisson客户端自动配置，无论是网关模块还是微服务模块，都进行注入。
 */
@Configuration
@ConditionalOnClass(RedisClientConfigProperties.class)
@EnableConfigurationProperties(RedisClientConfigProperties.class)
public class RedisClientAutoConfigure {

    @Bean("redissonClient")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext, RedisClientConfigProperties configProperties){
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://"+configProperties.getHost()+":"+configProperties.getPort())
                .setConnectionPoolSize(configProperties.getPoolSize())
                .setConnectionMinimumIdleSize(configProperties.getMinIdleSize())
//                                                .setUsername(configProperties.getUsername())
//                                                        .setPassword(configProperties.getPassword())
                .setDatabase(0);
//        config.setCodec(new Kryo5Codec());
        config.setCodec(JsonJacksonCodec.INSTANCE);
        return Redisson.create(config);
    }


    @Bean("ServicesLoginEvent")
    public ServicesLoginEvent servicesLoginEvent(ApplicationEventPublisher publisher){
        return  new ServicesLoginEvent(publisher);
    }
}

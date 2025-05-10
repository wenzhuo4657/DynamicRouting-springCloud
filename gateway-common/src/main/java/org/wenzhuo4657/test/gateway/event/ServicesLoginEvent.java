package org.wenzhuo4657.test.gateway.event;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.wenzhuo4657.test.gateway.support.impl.RouteHolder;

/**
 * 该类用语定义模块上下的通知模式
* */
@Slf4j
public class ServicesLoginEvent {

//     1， 发送、监听服务上线消息 2， 缓存、接受路由定义，

    private  String Tpoic="ServicesLoginEvent";

    private RedissonClient redissonClient;

    public static RouteHolder routeHolder;//   模块端专用



    public ServicesLoginEvent(ApplicationEventPublisher publisher) {

//        监听消息，发送刷新事件
        redissonClient.getTopic(Tpoic).addListener(ServicsLogin.class, (channel, message) -> {
            log.info("接收到消息：" + message.name);

            publisher.publishEvent(new RefreshRoutesEvent(this));//发布刷新事件

        });
    }



    public void Login(ServicsLogin message)
    {

        //    1,发送topic通知服务上线、服务路由定义添加
        redissonClient.getTopic(Tpoic).publish(message);

        //     2，缓存路由定义
//        todo 这里应当实现路由定义的自动读取，

    }


    public static class  ServicsLogin{
        public String name;
        public String route_id;
    }
}

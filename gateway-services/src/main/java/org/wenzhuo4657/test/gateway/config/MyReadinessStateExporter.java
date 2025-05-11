package org.wenzhuo4657.test.gateway.config;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyReadinessStateExporter {
//接收redis消息通知，发送网关加载消息通知组件动态加载




	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public MyReadinessStateExporter(RedissonClient redissonClient) {
		RTopic myTopic = redissonClient.getTopic("myTopic");
		myTopic.addListener(String.class, (channel, msg) -> {
			System.out.println(msg);
//			spring消息通知机制， 容器内
			applicationEventPublisher.publishEvent(new myEvent1(this));

		});


	}



	@EventListener
	public void onApplicationEvent(myEvent1 event) {

		System.out.println("收到网关加载消息通知组件动态加载");
		System.out.println(event.message);

		applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
	}
}
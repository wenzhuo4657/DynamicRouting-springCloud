package org.wenzhuo4657.test.gateway.config;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;

import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.wenzhuo4657.test.gateway.bean.MyRouteDefinition;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Component
public class MyReadinessStateExporter {


	@Autowired
	private RedissonClient redissonClient;

	String key="route_Map";
	RMap<String, MyRouteDefinition> map;
	@EventListener
	public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
		switch (event.getState()) {
			case ACCEPTING_TRAFFIC -> {

				RTopic myTopic = redissonClient.getTopic("myTopic");

				 map= redissonClient.getMap(key);
				MyRouteDefinition myRouteDefinition = new MyRouteDefinition();
				myRouteDefinition.route_name="test";
				myRouteDefinition.route_uri="http://localhost:8081";

				MyRouteDefinition.MyFilterDefinition myFilterDefinition = new MyRouteDefinition.MyFilterDefinition();
				myFilterDefinition.name="RewritePath";
				HashMap<String, String> objectObjectHashMap = new HashMap<>();
				objectObjectHashMap.put("regexp", "/api/(?<segment>.*)");
				objectObjectHashMap.put("replacement", "/${segment}");
				myFilterDefinition.args=objectObjectHashMap;


				MyRouteDefinition.MyPredicateDefinition myPredicateDefinition = new MyRouteDefinition.MyPredicateDefinition();
				myPredicateDefinition.name="Path";
				HashMap<String, String> objectObjectHashMap1 = new HashMap<>();
				objectObjectHashMap1.put("pattern", "/api/test/**");
				myPredicateDefinition.args=objectObjectHashMap1;

				myRouteDefinition.route_filter=myFilterDefinition;
				myRouteDefinition.route_predicate=myPredicateDefinition;

				map.put(myRouteDefinition.route_name,myRouteDefinition);
				myTopic.publish("模块上线，请求加载网关");
				// create file /tmp/healthy
			}
			case REFUSING_TRAFFIC -> {
				// remove file /tmp/healthy
			}
		}
	}

}
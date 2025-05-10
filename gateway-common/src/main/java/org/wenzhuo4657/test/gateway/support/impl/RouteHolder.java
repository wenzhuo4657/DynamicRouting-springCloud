package org.wenzhuo4657.test.gateway.support.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.wenzhuo4657.test.gateway.bean.MyRouteDefinition;
import org.wenzhuo4657.test.gateway.support.IRouteHolder;


import java.time.temporal.ChronoUnit;
import java.util.Objects;


public class RouteHolder  implements IRouteHolder {




    String  key="Router_id";
    RMap<String, MyRouteDefinition> map;


    private RedissonClient redissonClient;

    public RouteHolder(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        redissonClient.getMap(key);
    }

    @Override
    public MyRouteDefinition getById(String id){
        return map.get(id);
    }


    @Override
    public void  set(String id, MyRouteDefinition routeDefinition){
        map.put(id,routeDefinition);
    }

}

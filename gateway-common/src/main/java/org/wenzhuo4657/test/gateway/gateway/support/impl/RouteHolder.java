package org.wenzhuo4657.test.gateway.gateway.support.impl;



import lombok.Data;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.wenzhuo4657.test.gateway.gateway.bean.MyRouteDefinition;
import org.wenzhuo4657.test.gateway.gateway.support.IRouteHolder;




@Data
public class RouteHolder  implements IRouteHolder {




    String  key="Router_id";
    RMap<String, MyRouteDefinition> map;


    private RedissonClient redissonClient;

    public RouteHolder(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        map = redissonClient.getMap(key);
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


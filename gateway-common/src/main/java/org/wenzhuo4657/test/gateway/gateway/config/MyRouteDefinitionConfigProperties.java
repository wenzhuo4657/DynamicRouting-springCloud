package org.wenzhuo4657.test.gateway.gateway.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.wenzhuo4657.test.gateway.gateway.bean.MyRouteDefinition;


import java.util.Map;

@Data
@ConfigurationProperties(prefix = "dynamic.gateway.route", ignoreInvalidFields = true)
public class MyRouteDefinitionConfigProperties {
    public Map<String, MyRouteDefinition> routes;
}

package org.wenzhuo4657.test.gateway.bean;

import java.util.HashMap;

public class MyRouteDefinition {

    public String route_name;
    public String route_uri;
    public MyFilterDefinition route_filter;
    public MyPredicateDefinition route_predicate;





    public static class MyPredicateDefinition{
        public String name;
        public HashMap<String,String> args;

    }

    public static class MyFilterDefinition{
        public String name;
        public HashMap<String,String> args;
    }
}

package org.wenzhuo4657.test.gateway.config;

import org.springframework.context.ApplicationEvent;

public class myEvent1 extends ApplicationEvent {
    String message;
    public myEvent1(Object source) {
        super(source);
        this.message = "myEvent1";
    }

}

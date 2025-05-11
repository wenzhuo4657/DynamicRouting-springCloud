package org.wenzhuo4657.test.gateway.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("testController")
@RequestMapping("/test")
public class testController {

//    在模块中借用redis发布请求到网关模块中，然后官网再发送容器内消息通知加载服务，需要理解的是，这二者定义在虽然在同一个模块中，但是是两个不同的容器，借助第三方组件实现动态通知



    @PostMapping("/test")
    public String test() {
        return "test";
    }
}

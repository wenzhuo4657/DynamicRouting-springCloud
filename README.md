# 第一阶段开发目标
- 1，使用redissclient进行消息通知，写死在网关组件当中
- 2，自动注入配置，读取客户端中的路由定义，
- 3，提供给客户端一个发送路由更新的接口，默认发送在AvailabilityChangeEvent<ReadinessState>消息中，可通过配置关闭
- 4,通过注解区分网关行为、客户端行为

# learn-spring-amqp

## Run the demo

1. start rabbitmq
```
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

2. start consumer
```
gradle :consumer:bootrun
```

3. start producer
```
gradle :producer:bootrun
```
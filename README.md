# learn-spring-amqp

## Run the demo

* start rabbitmq
```
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

* start consumer
```
gradle :consumer:bootrun
```

* start producer
```
gradle :producer:bootrun
```
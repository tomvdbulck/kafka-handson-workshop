
# Image with both Zookeeper and Kafka, multiple nodes.



## Get started

Fill in the KAFKA_ADVERTISED_HOSTNAME

On a MAC:
```bash
> ipconfig getifaddr en0
```

With docker-machine:
```bash
> docker-machine ip default
```


Run docker-compose: 
```bash
> docker-compose up -d
```


If you want to scale up:
```bash
> docker-compose up -d --scale kafka=4
```

That will bring the kafka nodes to 4

In order to stop:
```
> docker-compose stop
```


## Play with it

Go to [Apache Kafka Quickstart](https://kafka.apache.org/quickstart) and download the latest release.
You can use the provided scripts to connect with the zookeeper and kafka of the docker container.

The docker container has the same version, by connecting to the docker container itself and navigating the the KAFKA_HOME folder you can execute the same scripts.

### Create a topic
Create a topic
```bash
> ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

Creating a topic with a replication > then the amount of kafka nodes will not be possible.

With the following command you can see the existing topics
```bash
> ./bin/kafka-topics.sh --list --zookeeper localhost:2181
```



### Produce messages
Replace the port number with a port from a docker container: docker ps

```bash
> ./bin/kafka-console-producer.sh --broker-list="localhost:9092" --topic test
This is a message
This is another message
```
### Consume messages
```bash
> ./bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
```

via bootstrap:
```bash
> ./bin/kafka-console-consumer.sh --bootstrap-server="localhost:32822" --topic test --from-beginning
```

Please note: restarting compose-up will register the kafka nodes again to zookeeper which means that they get a new broker id.
Which means that your old topics can no longer be mapped to a kafka node.

This can be mitigated by passing the --no-recreate option when rerunning docker-compose up. 
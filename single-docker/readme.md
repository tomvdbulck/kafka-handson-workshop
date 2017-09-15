
# Image with both Zookeeper and Kafka single node



## Get started

Build the image: 
```bash
docker build -t kafka-zookeeper .
```


Then run the container:
```bash
docker run --name a_name -i -t -p 2181:2181 -p 9092:9092 kafka-zookeeper
```

## Play with it

Go to [Apache Kafka Quickstart](https://kafka.apache.org/quickstart) and download the latest release.
You can use the provided scripts to connect with the zookeeper and kafka of the docker container.

The docker container has the same version, by connecting to the docker container itself and navigating the the KAFKA_HOME folder you can execute the same scripts.

### Create a topic
Create a topic
```bash
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

With the following command you can see the existing topics
```bash
bin/kafka-topics.sh --list --zookeeper localhost:2181
```


### Produce messages
```bash
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
This is a message
This is another message
```
### Consume messages
```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```

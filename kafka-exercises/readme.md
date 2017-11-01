
# Workshop exercises:




## Prerequisites: 

Open a shell and navigate to /single-docker.

Build the image: 
```bash
docker build -t kafka-zookeeper .
```


Then run the container:
```bash
docker run --name a_name -i -t -p 2181:2181 -p 9092:9092 kafka-zookeeper
```

If you run the container with the following command you can connect to your kafka from outside of the container
```bash
docker run --name kafka-zookeeper -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=9092 -i -t -p 2181:2181 -p 9092:9092 kafka-zookeeper
```

### Play with it

Go to [Apache Kafka Quickstart](https://kafka.apache.org/quickstart) and download the latest release.
You can use the provided scripts to connect with the zookeeper and kafka of the docker container.

The docker container has the same version, by connecting to the docker container itself and navigating the the KAFKA_HOME folder you can execute the same scripts.
```bash
> docker ps
> docker exec -it <CONTAINER_ID> bash

> cd opt/kafka_2.11-0.11.0.1/
```



##### Create a topic
Create a topic
```bash
> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

With the following command you can see the existing topics
```bash
> bin/kafka-topics.sh --list --zookeeper localhost:2181
```


##### Produce messages

If you did not pass the ADVERTISED_HOST you will need to open a shell to your docker container and run the commands from within the container.
```bash
> docker exec -it [container-id or container-name] bash

> cd opt/kafka_2.11-0.11.0.1
```

```bash
> bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
This is a message
This is another message
```
#### Consume messages
```bash
> bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```



### Postman

Install Postman: [https://www.getpostman.com/](https://www.getpostman.com/)

Import the collection: kafka-workshop.postman_collection.json.


## Exercises

### Native

#### Producer

Implement in NativeProducer.java the sendMessages() method.

When implemented send the postman request.
POST to http://localhost:8080/native:
```
{
"messages":["message1", "message2"], 
"topic":"test"
}
```

By default when you send a message to a topic which has not been created - it will be created automatically.


Verify with http://localhost:8080/topics that the topic has indeed been created.

#### Consumer

Implement in NativeConsumer.java the readMessages() method.

When done, test with:`
GET to http://localhost:8080/native?topic=test: 

#### Some remarks: 
If you run the method again - you will see no more messages.
Think about the offset.

POST some other messages and run the GET again => this should return the existing messages.

You can also only consume messages which are fully committed.


### Reactor

Now lets go the reactive way.

### Producer

Implement in SimpleProducer.java the sendMessages() method.
See [reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleProducer.java](reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleProducer.java) for sample reactive producer. 

When implemented send the postman request.
POST to http://localhost:8080/reactor:
```
{
"messages":["messageReactor1", "messageReactor2"], 
"topic":"test"
}
```



#### Consumer

Implement in SimpleConsumer.java the readMessages() method.
See [reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleConsumer.java](reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleConsumer.java) for sample reactive consumer.

When done, test with:`
GET to http://localhost:8080/reactor?topic=test: 

##### Remarks
Use the latch.countDown() to countdown the messages you have received an generate the interrupt faster so you do not have to await on the .await()


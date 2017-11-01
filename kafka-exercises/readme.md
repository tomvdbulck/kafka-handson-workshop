
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

### Play with it *optional*

Go to [Apache Kafka Quickstart](https://kafka.apache.org/quickstart) and download the latest release.
You can use the provided scripts to connect with the zookeeper and kafka of the docker container.

The docker container has the same version, by connecting to the docker container itself and navigating the the KAFKA_HOME folder you can execute the same scripts.
```bash
> docker ps
> docker exec -it <CONTAINER_ID> bash

> cd opt/kafka_2.11-0.11.0.1/
```



##### Create a topic *optional*
Create a topic
```bash
> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic mytest
```

With the following command you can see the existing topics
```bash
> bin/kafka-topics.sh --list --zookeeper localhost:2181
```


##### Produce messages *optional*


```bash
> bin/kafka-console-producer.sh --broker-list localhost:9092 --topic mytest
This is a message
This is another message
```
#### Consume messages
```bash
> bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mytest --from-beginning
```



### Postman

Install Postman: [https://www.getpostman.com/](https://www.getpostman.com/)

Import the collection: kafka-workshop.postman_collection.json.

Disclaimer: I just ran the export ;-)


## Exercises

### 1. Native

#### 1.1 Producer

Implement in NativeProducer.java the sendMessages() method.
See [javadoc](https://kafka.apache.org/100/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html)

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

#### 1.2 Consumer

Implement in NativeConsumer.java the readMessages() method.
See [Javadoc](https://kafka.apache.org/100/javadoc/index.html?org/apache/kafka/clients/consumer/KafkaConsumer.html)

When done, test with:`
GET to http://localhost:8080/native?topic=test: 

#### 1.3 Some remarks: 
If you run the method again - you will see no more messages.
Think about the offset.

POST some other messages and run the GET again => this should return the existing messages.

You can also only consume messages which are fully committed.


### 2. Reactor

Now lets go the reactive way.

#### 2.1 Producer

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

You can update the call the nativeConsumer to see if messages effectively got produced.

#### 2.2 Consumer

Implement in SimpleConsumer.java the readMessages() method.
See [reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleConsumer.java](reactor-kafka-samples/src/main/java/reactor/kafka/samples/SampleConsumer.java) for sample reactive consumer.

When done, test with:`
GET to http://localhost:8080/reactor?topic=test: 

##### 2.3 Remarks
Use the latch.countDown() to countdown the messages you have received an generate the interrupt faster so you do not have to await on the .await()

### 3. Spring Cloud

#### 3.1 Producer

Define an output channel with the name testCloud in OutputChannels.java.

Implement sendMessages in CloudProducer, use the outputchannel you defined.
When implemented send the postman request.
POST to http://localhost:8080/springcloudstream:
```
{
"messages":["messageCloud1", "messageBewolkt"], 
"topic":"test"
}
```

The design is indeed not perfect - topic:test is not used by this producer.

You can update the call the nativeConsumer to see if messages effectively got produced.

#### 3.2 Consumer

Implement the StreamHandler.

Spring Cloud streams is not ideally suited for this use case ... normally seen you subscribe to a topic and then you process the messages when these are published.

The StreamHandler uses some fuzzy logic to capture every message you send to testCloud, as soon as you startup the application.
When you request messages - it stops the consumer and returns the already collected list - then subscribes again on the topic.


#### 3.3 RabbitMQ
Download RabbitMQ [here](https://www.rabbitmq.com/download.html)

Go to `localhost:15672` and login with guest/guest.

Now go to application.yml and set the `defaultCandidate` of kafka to false and that of rabbitMQ to true.
Do check the port config of RabbitMQ.

The same logic should now also work for RabbitMQ.

Calling the nativeConsumer should now not return the messages you produced on RabbitMQ.
If it still does, this exercise is not complete as you are still not writing to RabbitMQ.
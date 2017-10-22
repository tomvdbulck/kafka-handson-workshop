package be.ordina.workshop.kafkaexercises.kafka_native;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;


//TODO rework to use MockProducer

public class NativeProducerTest {

    private NativeProducer nativeProducer = new NativeProducer();

    @Before
    public void setup() {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //TopicService topicService = new TopicService(AdminClient.create(config));
        //topicService.createTopic("test", 1, (short) 1);
    }

    @Test
    @Ignore
    public void sendMessageTest() {

        List<String> messages = new ArrayList<>();
        messages.add("kafka_native");
        messages.add("mokka");
        messages.add("pistache");
        messages.add("strawberry");

        //List<Future> futures = nativeProducer.sendMessages("test", messages);

    }

    @Test
    public void sendMessageToANotExistingTopicTest() {

        List<String> messages = new ArrayList<>();
        messages.add("I will never arrive");

        //List<Future> futures = nativeProducer.sendMessages("not_existing_topic", messages);

    }
}

package be.ordina.workshop.kafkaexercises.vanilla;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;


//TODO rework to use MockProducer

public class VanillaProducerTest {

    private VanillaProducer vanillaProducer = new VanillaProducer();

    @Before
    public void setup() {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        TopicService topicService = new TopicService(AdminClient.create(config));
        topicService.createTopic("test", 1, (short) 1);
    }

    @Test
    public void sendMessageTest() {

        List<String> messages = new ArrayList<>();
        messages.add("vanilla");
        messages.add("mokka");
        messages.add("pistache");
        messages.add("strawberry");

        List<Future> futures = vanillaProducer.sendMessages("test", messages);

    }

    @Test
    public void sendMessageToANotExistingTopicTest() {

        List<String> messages = new ArrayList<>();
        messages.add("I will never arrive");

        List<Future> futures = vanillaProducer.sendMessages("not_existing_topic", messages);

    }
}

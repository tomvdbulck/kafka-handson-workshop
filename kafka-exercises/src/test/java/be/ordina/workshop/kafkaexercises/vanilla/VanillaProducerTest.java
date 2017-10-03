package be.ordina.workshop.kafkaexercises.vanilla;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


//TODO rework to use MockProducer

public class VanillaProducerTest {

    private VanillaProducer vanillaProducer = new VanillaProducer();

    @Before
    public void setup() {
        TopicService topicService = new TopicService();
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

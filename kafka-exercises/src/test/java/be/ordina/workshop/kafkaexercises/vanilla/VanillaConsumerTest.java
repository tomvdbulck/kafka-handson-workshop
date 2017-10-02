package be.ordina.workshop.kafkaexercises.vanilla;

import org.junit.Before;
import org.junit.Test;

public class VanillaConsumerTest {

    private VanillaConsumer consumer = new VanillaConsumer();

    @Before
    public void setup() {
        TopicService topicService = new TopicService();
        topicService.createTopic("test", 1, (short) 1);
    }

    @Test
    public void consumeMessagesTest() {

        consumer.readMessages("test");

    }

    @Test
    public void consumeMessagesFromAnUnexistingTopicTest() {
        consumer.readMessages("i-do-not-exist");
    }
}

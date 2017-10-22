package be.ordina.workshop.kafkaexercises.kafka_native;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Properties;

//TODO rework to use MockConsumer
public class NativeConsumerTest {

    private NativeConsumer consumer = new NativeConsumer();

    @Before
    public void setup() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //TopicService topicService = new TopicService(AdminClient.create(config));
        //topicService.createTopic("test", 1, (short) 1);
    }

    @Test
    @Ignore
    public void consumeMessagesTest() {

        consumer.readMessages("test");

    }

    @Test
    @Ignore
    public void consumeMessagesFromAnUnexistingTopicTest() {
        consumer.readMessages("i-do-not-exist");
    }
}

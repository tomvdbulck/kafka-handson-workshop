package be.ordina.workshop.kafkaexercises.vanilla;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;


public class TopicServiceTest {

    private TopicService topicService;


    @Before
    public void setup() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");

        topicService = new TopicService(AdminClient.create(config));
    }

    @Test
    public void createTopicTest() {

        topicService.createTopic("test", 1, (short) 1);


    }




}

package be.ordina.workshop.kafkaexercises.vanilla;

import org.junit.Before;
import org.junit.Test;


public class TopicServiceTest {

    private TopicService topicService = new TopicService();

    @Test
    public void createTopicTest() {

        topicService.createTopic("test", 1, (short) 1);


    }




}

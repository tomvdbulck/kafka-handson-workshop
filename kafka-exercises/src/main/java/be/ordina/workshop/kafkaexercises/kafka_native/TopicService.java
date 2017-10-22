package be.ordina.workshop.kafkaexercises.kafka_native;

import org.apache.kafka.clients.admin.*;
import org.springframework.stereotype.Component;

import java.util.*;


public class TopicService {

    private final AdminClient admin;

    public TopicService(AdminClient adminClient) {
        admin = adminClient;
    }


    public CreateTopicsResult createTopic(final String name, final Integer partitions, final Short replication) {
        List newTopics = new ArrayList<NewTopic>();

        newTopics.add(new NewTopic(name, partitions, replication));

        return admin.createTopics(newTopics);

    }

    public ListTopicsResult listTopics() {
        return admin.listTopics();

    }

    public DescribeTopicsResult describeTopic(List<String> topics) {
        return admin.describeTopics(topics);
    }

}

package be.ordina.workshop.kafkaexercises.vanilla;

import org.apache.kafka.clients.admin.*;

import java.util.*;

public class TopicService {

    private final AdminClient admin;

    public TopicService() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");

        admin = AdminClient.create(config);
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

package be.ordina.workshop.kafkaexercises.kafka_native;

import be.ordina.workshop.kafkaexercises.model.Topics;
import be.ordina.workshop.kafkaexercises.model.TopicsDescriptions;
import be.ordina.workshop.kafkaexercises.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class TopicService {

    private final AdminService adminService;

    public TopicService(AdminService adminService) {
        this.adminService = adminService;
    }


    public CreateTopicsResult createTopic(final String name, final Integer partitions, final Short replication) {
        List newTopics = new ArrayList<NewTopic>();

        newTopics.add(new NewTopic(name, partitions, replication));

        return adminService.getAdminClient().createTopics(newTopics);

    }

    public Topics listTopics() {


        ListTopicsResult listTopicsResult = adminService.getAdminClient().listTopics();

        try {
            while(!listTopicsResult.names().isDone()) {
                log.info("Waiting on data...");

                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            log.error("Interrupted ..." + e);
        }

        Topics topics = new Topics();
        try {
            topics.setTopics(new ArrayList<>(listTopicsResult.names().get()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }

        return topics;

    }

    public TopicsDescriptions describeTopic(List<String> topics) {

        DescribeTopicsResult describeTopicsResult = adminService.getAdminClient().describeTopics(topics);

        try {
            while(!describeTopicsResult.all().isDone()) {
                log.info("Waiting on data...");

                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            log.error("Interrupted ..." + e);
        }

        TopicsDescriptions topicsDescriptions = new TopicsDescriptions();
        describeTopicsResult.values().forEach((k, v) -> {
            try {
                topicsDescriptions.getDescriptions().add(v.get().toString());

            } catch(Exception e) {
                log.error(e.getMessage());
            }

        });


        return topicsDescriptions;
    }

}

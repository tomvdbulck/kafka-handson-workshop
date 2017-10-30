package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.kafka_native.TopicService;
import be.ordina.workshop.kafkaexercises.model.Topics;
import be.ordina.workshop.kafkaexercises.model.TopicsDescriptions;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(final TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping()
    public ResponseEntity<Topics> listTopics() {

        return ResponseEntity.ok(topicService.listTopics());
    }

    @GetMapping(value = "/describe")
    public ResponseEntity<TopicsDescriptions> describeTopics(final @RequestParam(value = "topics") List<String> topics) {
        return  ResponseEntity.ok(topicService.describeTopic(topics));
    }


}

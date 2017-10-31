package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import be.ordina.workshop.kafkaexercises.spring_cloud.CloudProducer;
import be.ordina.workshop.kafkaexercises.spring_cloud.StreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/springcloudstream")
public class SpringCloudStreamKafkaController implements KafkaController{

    private Boolean connectToKafka;

    private final CloudProducer cloudProducer;
    private final StreamHandler streamHandler;

    @Autowired
    public SpringCloudStreamKafkaController(final CloudProducer cloudProducer, final StreamHandler streamHandler) {
        this.cloudProducer = cloudProducer;
        this.streamHandler = streamHandler;
        connectToKafka = false;
    }

    @Override
    @GetMapping
    public ResponseEntity<ReadResponse> getMessages(@RequestParam final String topic) {

        return ResponseEntity.ok(new ReadResponse(streamHandler.getMessages()));
    }

    @Override
    @PostMapping
    public ResponseEntity writeMessages(@RequestBody final WriteRequest request) {
        cloudProducer.sendMessages(request.getMessages());

        return ok().build();
    }


    @PostMapping(value = "connectToKafka")
    public ResponseEntity connectToKafka (@RequestParam final boolean connectToKafka) {
        this.connectToKafka = connectToKafka;

        return ok(null);
    }
}

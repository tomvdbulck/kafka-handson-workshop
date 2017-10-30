package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import be.ordina.workshop.kafkaexercises.reactor.SimpleConsumer;
import be.ordina.workshop.kafkaexercises.reactor.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@RestController
@RequestMapping("/reactor")
public class ReactorController implements KafkaController {

    private final SimpleProducer simpleProducer;
    private final SimpleConsumer simpleConsumer;

    @Autowired
    public ReactorController(final SimpleConsumer simpleConsumer, final SimpleProducer simpleProducer) {
        this.simpleConsumer = simpleConsumer;
        this.simpleProducer = simpleProducer;
    }


    @Override
    @GetMapping
    public ResponseEntity<ReadResponse> getMessages(@RequestParam String topic) {

        return ok(new ReadResponse(simpleConsumer.readMessages(topic, null)));
    }


    @Override
    @PostMapping
    public ResponseEntity writeMessages(@RequestBody WriteRequest request) {

        simpleProducer.sendMessages(request.getTopic(), request.getMessages());

        return ok("wrote messages for request:" + request );
    }
}

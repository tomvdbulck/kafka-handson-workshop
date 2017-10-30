package be.ordina.workshop.kafkaexercises.controller;


import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.kafka_native.NativeConsumer;
import be.ordina.workshop.kafkaexercises.kafka_native.NativeProducer;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/native")
@Slf4j
public class NativeController implements  KafkaController{

    private final NativeConsumer consumer;

    private final NativeProducer producer;

    public NativeController(final NativeConsumer nativeConsumer, final NativeProducer nativeProducer) {
        this.consumer = nativeConsumer;
        this.producer = nativeProducer;
    }


    @Override
    @GetMapping
    public ResponseEntity<ReadResponse> getMessages(@RequestParam(name = "topic") final String topic) {

        log.info("Reading messages from topic: " + topic);


        return ok(new ReadResponse(consumer.readMessages(topic)));
    }

    @Override
    @PostMapping
    public ResponseEntity writeMessages(@RequestBody final WriteRequest request) {

        log.info("Sending messages to topic: " + request.getTopic());

        producer.sendMessages(request.getTopic(), request.getMessages());

        return ok("wrote messages for request:" + request );

    }

}

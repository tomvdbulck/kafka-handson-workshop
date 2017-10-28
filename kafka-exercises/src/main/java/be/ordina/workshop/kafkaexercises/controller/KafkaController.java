package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


public interface KafkaController {

    ResponseEntity<ReadResponse> getMessages(@RequestParam final String topic);

    ResponseEntity writeMessages(@RequestBody final WriteRequest request);


}

package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reactor")
public class ReactorController implements KafkaController {


    @Override
    @GetMapping
    public ResponseEntity<ReadResponse> getMessages(@RequestParam String topic) {
        return null;
    }


    @Override
    @PostMapping
    public ResponseEntity writeMessages(@RequestBody WriteRequest request) {
        return null;
    }
}

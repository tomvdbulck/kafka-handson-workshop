package be.ordina.workshop.kafkaexercises.controller;

import be.ordina.workshop.kafkaexercises.model.ReadResponse;
import be.ordina.workshop.kafkaexercises.model.WriteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/springcloudstream")
public class SpringCloudStreamKafkaController implements KafkaController{

    private Boolean connectToKafka;

    public SpringCloudStreamKafkaController() {
        connectToKafka = false;
    }

    @Override
    @GetMapping
    public ResponseEntity<ReadResponse> getMessages(@RequestParam final String topic) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity writeMessages(@RequestBody final WriteRequest request) {
        return null;
    }


    @PostMapping(value = "connectToKafka")
    public ResponseEntity connectToKafka (@RequestParam final boolean connectToKafka) {
        this.connectToKafka = connectToKafka;

        return ResponseEntity.ok(null);
    }
}

package be.ordina.workshop.kafkaexercises.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TopicsDescriptions {
    private final List<String> descriptions;

    public TopicsDescriptions() {
        this.descriptions = new ArrayList<>();
    }

}

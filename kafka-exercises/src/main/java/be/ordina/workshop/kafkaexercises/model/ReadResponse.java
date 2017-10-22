package be.ordina.workshop.kafkaexercises.model;

import java.util.ArrayList;
import java.util.List;

public class ReadResponse {

    private final List<String> messages;

    public ReadResponse() {
        messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return  messages;
    }
}

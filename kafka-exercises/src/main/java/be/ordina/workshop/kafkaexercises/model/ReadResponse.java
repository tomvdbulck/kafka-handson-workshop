package be.ordina.workshop.kafkaexercises.model;

import java.util.List;

public class ReadResponse {

    private final List<String> messages;

    public ReadResponse(final List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return  messages;
    }
}

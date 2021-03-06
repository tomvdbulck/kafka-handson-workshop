package be.ordina.workshop.kafkaexercises.spring_cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class StreamHandler {

    private List<String> messages;
    private final InputChannels inputChannels;

    private MessageHandler messageHandler;

    @Autowired
    public StreamHandler(InputChannels inputChannels) {
        this.messages = new ArrayList<>();
        this.inputChannels = inputChannels;

        subScribeOnChannel();
    }

    private void subScribeOnChannel() {


        this.messages = new ArrayList<>();

        messageHandler = (message -> {
            log.info("retrieved message " + message.getPayload());
            messages.add((String) message.getPayload());
        });


        //TODO you need to do something with that messageHandler ... but what ...
    }

    public List<String> getMessages() {

        List<String> messagesToReturn = new ArrayList<>();

        //TODO return the messages


        return messagesToReturn;
    }

}

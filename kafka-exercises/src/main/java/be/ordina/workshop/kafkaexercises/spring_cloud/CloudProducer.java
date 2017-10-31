package be.ordina.workshop.kafkaexercises.spring_cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CloudProducer {

    private final OutputChannels outputChannels;

    @Autowired
    public CloudProducer(OutputChannels outputChannels) {
        this.outputChannels = outputChannels;
    }

    public void sendMessages(List<String> messages) {

        messages.forEach(m -> {
            log.info("send message: " + m + " to testCloud");
            outputChannels.testCloud().send(MessageBuilder.withPayload(m).build());});

    }
}

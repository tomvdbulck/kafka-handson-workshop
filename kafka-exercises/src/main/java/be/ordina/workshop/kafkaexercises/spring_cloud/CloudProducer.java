package be.ordina.workshop.kafkaexercises.spring_cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CloudProducer {

    private final OutputChannels outputChannels;

    @Autowired
    public CloudProducer(final OutputChannels outputChannels) {
        this.outputChannels = outputChannels;
    }

    public void sendMessages(List<String> messages) {

        //TODO implement me

    }
}

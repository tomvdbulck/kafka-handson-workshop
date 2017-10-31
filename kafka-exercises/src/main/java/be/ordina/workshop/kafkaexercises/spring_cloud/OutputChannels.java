package be.ordina.workshop.kafkaexercises.spring_cloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannels {

    @Output
    MessageChannel testCloud();

}

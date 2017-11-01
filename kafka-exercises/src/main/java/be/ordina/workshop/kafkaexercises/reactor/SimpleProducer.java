package be.ordina.workshop.kafkaexercises.reactor;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class SimpleProducer {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    private final SimpleDateFormat dateFormat;

    private SenderOptions<Integer, String> senderOptions;

    public SimpleProducer() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        senderOptions = SenderOptions.create(props);

        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public void sendMessages(final String topic, final List<String> messages)  {
        final KafkaSender<Integer, String> sender = KafkaSender.create(senderOptions);

        final CountDownLatch latch = new CountDownLatch(messages.size());

        try {

            //TODO use the sender.send( ...

            sender.close();

            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

    }
}

package be.ordina.workshop.kafkaexercises.reactor;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SimpleConsumer {
    private final ReceiverOptions<Integer, String> receiverOptions;
    private final SimpleDateFormat dateFormat;

    private KafkaReceiver kafkaReceiver;

    public SimpleConsumer() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactor-consumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        receiverOptions = ReceiverOptions.create(props);
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public List<String> readMessages(String topic) {

        List<String> messages = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(20);

        try {

            if (kafkaReceiver == null) {
                ReceiverOptions<Integer, String> options = receiverOptions.subscription(Collections.singleton(topic))
                        .addAssignListener(partitions -> log.info("onPartitionsAssigned {}", partitions))
                        .addRevokeListener(partitions -> log.info("onPartitionsRevoked {}", partitions));

                kafkaReceiver = KafkaReceiver.create(options);
            }


            Flux<ReceiverRecord<Integer, String>> kafkaFlux = kafkaReceiver.receive();
            Disposable disposable = null;
            //TODO use the Flux, Luke ...

            latch.await(2, TimeUnit.SECONDS);

            if (disposable != null) {
                disposable.dispose();
            }

        }catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return messages;
    }

}

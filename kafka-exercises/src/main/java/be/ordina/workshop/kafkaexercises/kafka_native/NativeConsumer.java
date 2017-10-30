package be.ordina.workshop.kafkaexercises.kafka_native;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class NativeConsumer {


    private final Properties props;


    public NativeConsumer() {
        props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "your_client_id");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    }


    public List<String> readMessages(final String ... topics) {

        List<String> messages = new ArrayList<>();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topics));

        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            log.info("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            messages.add(record.value());
        }
        if (records.isEmpty()) {
            log.info("no messages found on " + topics);
        }

        consumer.close();

        return messages;

    }



}

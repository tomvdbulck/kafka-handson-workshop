package be.ordina.workshop.kafkaexercises;

import be.ordina.workshop.kafkaexercises.spring_cloud.OutputChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(OutputChannels.class)
public class KafkaExercisesApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaExercisesApplication.class, args);

	}
}

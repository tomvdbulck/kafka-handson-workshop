package be.ordina.workshop.kafkaexercises.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
public class AdminService {

    private final Properties config;

    private AdminClient adminClient;

    public AdminService() {
        config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    }

    public AdminClient getAdminClient() {
        if (adminClient == null) {
            adminClient = AdminClient.create(config);
        }

        return adminClient;
    }
}

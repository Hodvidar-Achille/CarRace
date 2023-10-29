package com.hodvidar.carrace.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hodvidar.carrace.domain.Race;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class RaceCreationProducer {

    @Value(value = "${kafka.topic.createRace}")
    private String topicCreateRace;

    private final KafkaProducer<String, String> producer;

    @Autowired
    public RaceCreationProducer(@Value(value = "${spring.kafka.producer.bootstrap-servers}") final String bootstrapServers) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(properties);
    }

    public void send(Race raceCreated) {
        Executors.newSingleThreadExecutor().submit(() -> sendAsync(raceCreated));
    }

    private void sendAsync(final Race raceCreated) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String raceJson = objectMapper.writeValueAsString(raceCreated);
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(topicCreateRace, raceJson);
            // send data - asynchronous
            producer.send(producerRecord, (recordMetadata, e) -> {
                if (e == null) {
                    log.info("Received new metadata. \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {
                    log.error("Error while producing", e);
                }
            });
            /*
            // flush data - synchronous
            producer.flush();
            // flush and close producer
            producer.close();
            */
        } catch (JsonProcessingException e) {
            log.error("Error while converting Race object to JSON", e);
        }
    }

}
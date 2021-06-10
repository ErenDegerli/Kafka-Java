package com.task;

import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class App {
    public static void main(String[] args) {
		runProducer();
        runConsumer();
    }

    static void runConsumer() {
        Consumer<Long, Person> consumer = ConsumerCreator.createConsumer();

        int noMessageToFetch = 0;

        while (true) {
            final ConsumerRecords<Long, Person> consumerRecords = consumer.poll(1000);
            if (consumerRecords.count() == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > KafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {
                System.out.println("Record " + record);
                System.out.println("Record topic: " + record.topic());
                System.out.println("Record partition " + record.partition());
                System.out.println("Record offset " + record.offset());
                System.out.println("Record ID: " + record.value().getId());
                System.out.println("Record Name: " + record.value().getName());
                System.out.println("Does contain Can? : " + record.value().getName().contains("Can"));
            });
            consumer.commitAsync();
        }
        consumer.close();
    }

    static void runProducer() {
        Producer<Long, Person> producer = ProducerCreator.createProducer();


        for (int index = 0; index < KafkaConstants.MESSAGE_COUNT; index++) {

            Person person = new PersonBuilder().withId(index).withName("Can" + index).build();

            final ProducerRecord<Long, Person> record = new ProducerRecord<Long, Person>(KafkaConstants.TOPIC_NAME,
                    person);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException | InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}

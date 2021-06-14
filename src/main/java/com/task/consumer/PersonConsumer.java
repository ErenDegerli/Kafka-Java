package com.task.consumer;

import com.task.constants.KafkaConstants;
import com.task.entity.Person;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class PersonConsumer extends ConsumerBase<Person> {

    @Override
    public void consume() {
            Consumer<Long, Person> consumer = createConsumer();

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
}

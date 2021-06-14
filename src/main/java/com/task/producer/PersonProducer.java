package com.task.producer;

import com.task.constants.KafkaConstants;
import com.task.entity.Person;
import com.task.builders.PersonBuilder;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.concurrent.ExecutionException;

public class PersonProducer extends ProducerBase<Person>{

    @Override
    public void produce(String name, int count) {
        Producer<Long, Person> producer = createProducer();

        for (int index = 0; index < count; index++) {

            Person person = new PersonBuilder().withId(index).withName(name + index).build();

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

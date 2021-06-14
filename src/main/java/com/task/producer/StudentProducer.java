package com.task.producer;

import com.task.builders.StudentBuilder;
import com.task.constants.KafkaConstants;
import com.task.entity.Student;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.concurrent.ExecutionException;

public class StudentProducer extends ProducerBase<Student>{

    @Override
    public void produce(String name, int count) {
        Producer<Long, Student> producer = createProducer();

        for (int index = 0; index < count; index++) {

            Student student = new StudentBuilder().withId(index).withName(name + index).build();

            final ProducerRecord<Long, Student> record = new ProducerRecord<Long, Student>(KafkaConstants.TOPIC_NAME, student);
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

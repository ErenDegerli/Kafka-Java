package com.task;

import com.task.consumer.PersonConsumer;
import com.task.entity.Person;
import com.task.producer.PersonProducer;

public class Runner {

    public static void main(String[] args) {

        PersonProducer<Person> producer = new PersonProducer<>();
        producer.produce("Eren", 10);

        PersonConsumer<Person> consumer = new PersonConsumer<>();
        consumer.consume();
    }
}

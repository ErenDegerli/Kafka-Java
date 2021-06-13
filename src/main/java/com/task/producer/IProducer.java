package com.task.producer;

public interface IProducer<T> {

    void produce(String name, int count);
}

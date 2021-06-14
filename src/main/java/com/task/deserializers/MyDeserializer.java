package com.task.deserializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class MyDeserializer<T> {/*implements Deserializer<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<T> typeReference = new TypeReference<T>() {
    };


    public MyDeserializer() {

    }

    @Override
    public T serialize(final String topic, final byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            return mapper.readValue(data, typeReference);
        } catch (final IOException ex) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) + "] from topic [" + topic + "]", ex);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(final Map<String, ?> settings, final boolean isKey) {
    }*/
}
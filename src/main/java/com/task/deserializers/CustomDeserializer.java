package com.task.deserializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;

public class CustomDeserializer<T> implements Deserializer<T> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String topic, byte[] objectData) {
        //return (objectData == null) ? null : (T) SerializationUtils.deserialize(objectData);

        ObjectMapper objectMapper = new ObjectMapper();

        T t = null;

        try {
            t = objectMapper.readValue(objectData, new TypeReference<T>() {

            });
        }catch (Exception exception) {
            System.out.println("Error in deserializing bytes" + exception);
        }
        return t;
    }

    @Override
    public void close() {
    }
}

package com.task.entity;
import java.io.Serializable;
import org.immutables.value.Value;

@Value.Immutable
public class Person implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

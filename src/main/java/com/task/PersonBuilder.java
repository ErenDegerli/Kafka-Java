package com.task;

public class PersonBuilder {

    private int id = 1;
    private String name = "Eren";

    public PersonBuilder withId(int id){
        this.id = id;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Person build() {
        return new Person() {{
            setId(id);
            setName(name);
        }};
    }
}

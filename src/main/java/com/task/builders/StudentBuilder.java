package com.task.builders;

import com.task.entity.Student;


public class StudentBuilder {

    private int id = 1;
    private String name = "StudentEren";

    public StudentBuilder withId(int id){
        this.id = id;
        return this;
    }

    public StudentBuilder withName(String name) {
        this.name = "student" + name;
        return this;
    }

    public Student build() {
        return new Student() {{
            setId(id);
            setName(name);
        }};
    }
}

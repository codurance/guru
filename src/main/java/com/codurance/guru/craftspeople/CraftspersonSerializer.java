package com.codurance.guru.craftspeople;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CraftspersonSerializer extends StdSerializer<Craftsperson> {

    protected CraftspersonSerializer() {
        super(Craftsperson.class);
    }

    @Override
    public void serialize(Craftsperson craftsperson, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Person mentor = null;
        if(craftsperson.getMentor() != null) {
            mentor = new Person(craftsperson.getMentor());
        }
        List<Person> mentees = craftsperson.getMentees().stream()
                .map(Person::new)
                .collect(Collectors.toList());
        gen.writeObject(new SerializableCraftsperson(craftsperson, mentor, mentees));
    }
}

class Person {
    private Integer id;
    private String firstName;
    private String lastName;

    public Person(Craftsperson craftsperson) {
        this(craftsperson.getId(), craftsperson.getFirstName(), craftsperson.getLastName());
    }

    public Person(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class SerializableCraftsperson extends Person {
    private Person mentor;
    private List<Person> mentees;

    public SerializableCraftsperson(Craftsperson craftsperson, Person mentor, List<Person> mentees) {
        super(craftsperson);
        this.mentor = mentor;
        this.mentees = mentees;
    }

    public Person getMentor() {
        return mentor;
    }

    public void setMentor(Person mentor) {
        this.mentor = mentor;
    }

    public List<Person> getMentees() {
        return mentees;
    }

    public void setMentees(List<Person> mentees) {
        this.mentees = mentees;
    }
}
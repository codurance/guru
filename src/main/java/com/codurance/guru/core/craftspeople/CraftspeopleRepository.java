package com.codurance.guru.core.craftspeople;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CraftspeopleRepository  {

    List<Craftsperson> findByFirstNameAndLastName(String firstName, String lastName);

    void updateLastMeeting(Integer id, Instant lastMeeting);

    Craftsperson save(Craftsperson craftsperson);

    Optional<Craftsperson> findById(Integer id);

    void deleteById(Integer craftspersonId);

    List<Craftsperson> findAll();

    void removeMentor(int menteeId);

    void addMentor(int mentorId, int menteeId);

    Craftsperson create(String firstName, String lastName);
}

package com.codurance.guru.core.craftspeople;

import java.awt.font.OpenType;
import java.util.List;
import java.util.Optional;

public interface CraftspeopleRepository  {

    List<Craftsperson> findByFirstNameAndLastName(String firstName, String lastName);

    Craftsperson save(Craftsperson craftsperson);

    Optional<Craftsperson> findById(Integer id);

    void deleteById(Integer craftspersonId);

    List<Craftsperson> findAll();
}

package com.codurance.guru;

import com.codurance.guru.craftspeople.CraftspeopleRepository;
import com.codurance.guru.craftspeople.CraftspeopleService;
import com.codurance.guru.craftspeople.Craftsperson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GuruApplication.class)
@TestPropertySource(value={"classpath:application-test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CraftspeopleControllerShould {

    @Autowired
    CraftspeopleService craftspeopleService;

    @MockBean
    CraftspeopleRepository craftspeopleRepository;

    @Test
    public void retrieve_a_craftsperson_when_asked_for_one() {
        Optional<Craftsperson> craftsperson = Optional.of(new Craftsperson("John", "Doe"));
        when(craftspeopleRepository.findById(1)).thenReturn(craftsperson);

        assertTrue(craftspeopleService.retrieveCraftsperson(1).getFirstName().equals("John"));
    }
}

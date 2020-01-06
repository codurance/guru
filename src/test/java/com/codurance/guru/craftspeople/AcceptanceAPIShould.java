package com.codurance.guru.craftspeople;

import com.codurance.guru.GuruApplication;
import com.jayway.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GuruApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceAPIShould {

    @Autowired
    CraftspeopleRepository craftspeopleRepository;
    private Craftsperson savedCraftsperson;

    @Test
    public void retrieve_a_craftsperson() throws Exception{

        given_a_craftsperson_in_the_repository();

        RestAssured.get("craftspeople/{craftspersonId}", savedCraftsperson.getId())
                .then().assertThat()
                .body("id", equalTo(savedCraftsperson.getId()))
                .body("firstName", equalTo(savedCraftsperson.getFirstName()))
                .body("lastName", equalTo(savedCraftsperson.getLastName()));
    }

    private void given_a_craftsperson_in_the_repository() {
        savedCraftsperson = craftspeopleRepository.save(new Craftsperson("Arnaud","CLAUDEL"));
    }


}

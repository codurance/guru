package com.codurance.guru.infra.web.controllers;

import com.codurance.guru.core.craftspeople.CraftspeopleService;
import com.codurance.guru.core.craftspeople.Craftsperson;
import com.codurance.guru.infra.web.responses.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static com.codurance.guru.infra.web.responses.Responses.successResponse;

@Controller
@RequestMapping("craftspeople")
public class CraftspeopleController {

    @Autowired
    private CraftspeopleService craftspeopleService;

    @GetMapping
    public ResponseEntity<List<Craftsperson>> retrieveAll() {
        return successResponse(craftspeopleService.retrieveAllCraftsperson());
    }

    @GetMapping("{craftspersonId}")
    public ResponseEntity<Craftsperson> retrieveCraftsperson(@PathVariable Integer craftspersonId) {
        Optional<Craftsperson> retrievedCraftsperson = craftspeopleService.retrieveCraftsperson(craftspersonId);
        return retrievedCraftsperson
                .map(Responses::successResponse)
                .orElseGet(Responses::notFound);
    }

}
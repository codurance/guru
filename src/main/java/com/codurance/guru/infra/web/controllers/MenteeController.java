package com.codurance.guru.infra.web.controllers;

import com.codurance.guru.core.craftspeople.CraftspeopleService;
import com.codurance.guru.core.craftspeople.exceptions.DuplicateMenteeException;
import com.codurance.guru.core.craftspeople.exceptions.InvalidMentorRelationshipException;
import com.codurance.guru.infra.web.requests.AddMentorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.codurance.guru.infra.web.responses.ErrorResponse.*;
import static com.codurance.guru.infra.web.responses.SuccessResponse.successResponse;

@Controller
@RequestMapping("craftspeople/mentee")
public class MenteeController {

    @Autowired
    private CraftspeopleService craftspeopleService;

    @PutMapping("add")
    public ResponseEntity<?> setMentee(@Valid @RequestBody AddMentorRequest request) {
        try {
            craftspeopleService.setMentee(request.getMentorId(), request.getMenteeId());
            return successResponse();
        } catch (InvalidMentorRelationshipException ex) {
            return errorResponse("Cant add a craftsperson as their own mentor");
        } catch (DuplicateMenteeException ex) {
            return errorResponse("Mentee already exists");
        }
    }

    @PutMapping("remove/{menteeId}")
    public ResponseEntity<Void> removeMentee(@PathVariable int menteeId) {
        craftspeopleService.removeMentor(menteeId);
        return successResponse();
    }
}

package com.codurance.guru.infra.web.controllers;

import com.codurance.guru.core.configuration.lastmeeting.threshold.LastMeetingThreshold;
import com.codurance.guru.infra.repository.LastMeetingThresholdRepositoryImpl;
import com.codurance.guru.infra.web.responses.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.ResponseEntity.*;

@Controller
public class LastMeetingThresholdConfigController {

    @Autowired
    private LastMeetingThresholdRepositoryImpl lastMeetingThresholdConfigRepository;

    @GetMapping("/config")
    public ResponseEntity<LastMeetingThreshold> get() {
        return ok(lastMeetingThresholdConfigRepository.getConfig());
    }

    @PutMapping("/config")
    public ResponseEntity<ErrorResponse> update(@RequestBody LastMeetingThreshold lastMeetingThreshold) {
        if(lastMeetingThreshold.getLastMeetingThresholdsInWeeks() == null) {
            return badRequest().body(new ErrorResponse("Last meeting threshold cannot be null"));
        }

        if(lastMeetingThreshold.getLastMeetingThresholdsInWeeks() <= 0) {
            return badRequest().body(new ErrorResponse("Last meeting threshold must be greater than 0"));
        }

        lastMeetingThresholdConfigRepository.updateThreshold(lastMeetingThreshold.getLastMeetingThresholdsInWeeks());

        return noContent().build();
    }

}

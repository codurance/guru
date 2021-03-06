package com.codurance.guru.craftspeople.requests;

import javax.validation.constraints.NotNull;

public class RemoveMentorRequest {

    @NotNull(message = "Mentor Id must have a value")
    private int mentorId;

    @NotNull(message = "Mentee Id must have a value")
    private int menteeId;

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}

package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.Persona;

public class PersonaImpl implements Persona {
    private String deathCause;
    private String deathDetails;
    private Long timeOfWritingName;
    private Long deathCauseTime;
    private Boolean deathCauseSpecified;

    public Boolean isDeathCauseSpecified() {
        return this.deathCauseSpecified;
    }

    public Long getDeathCauseTime() {
        return deathCauseTime;
    }

    public void setDeathCauseTime(Long timeOfWritingCause) {
        this.deathCauseTime = timeOfWritingCause;
    }

    @Override
    public String getDeathDetails() {
        return deathDetails;
    }

    @Override
    public void setDeathDetails(String deathDetails) {
        this.deathDetails = deathDetails;
    }
       
    @Override
    public String getCauseOfDeath() {
        return deathCause;
    }

    @Override
    public void setDeathCause(String deathCause) {
        this.deathCause = deathCause;
        this.deathCauseSpecified = true; 
    }

    @Override
    public Long getTimeOfWritingName() {
        return timeOfWritingName;
    }

    public PersonaImpl() {
        this.timeOfWritingName = System.currentTimeMillis();
        this.deathCauseTime = this.timeOfWritingName;
        this.deathCause = "heart attack";
        this.deathDetails = "";
        this.deathCauseSpecified = false;
    }
}
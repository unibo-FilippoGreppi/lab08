package it.unibo.deathnote.api;

public interface Persona {

    String getDeathDetails();

    void setDeathDetails(String deathDetails);

    String getCauseOfDeath();

    void setDeathCause(String deathCause);

    Long getTimeOfWritingName();

    Long getDeathCauseTime();

    void setDeathCauseTime(Long timeOfWritingCause);
    
    Boolean isDeathCauseSpecified();
}
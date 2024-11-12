package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.Persona;

public class DeathNoteImplementation implements DeathNote {
    final static private int UNSUPPORTED_RULE = 0;
    final static private long CAUSE_TIME = 40;
    final static private long DETAILS_TIME = 6040;
    final private Map<String, Persona> deathNote = new HashMap<>();
    private String lastName = "";

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= UNSUPPORTED_RULE) {
            throw new IllegalArgumentException("Index can not be negative or 0");
        }
        if (ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (name.isBlank()) {
            throw new NullPointerException("Name can not be blank");
        }
        this.lastName = name;
        this.deathNote.putIfAbsent(name, new PersonaImpl());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (this.lastName == "") {
            throw new IllegalStateException("Last name can not be blank");
        }
        if (cause.isBlank()) {
            throw new IllegalStateException("Cause can not be blank");
        }
        Persona persona = this.deathNote.get(this.lastName);
        if (!persona.isDeathCauseSpecified()) {
            persona.setDeathCause(cause);
        }
        persona.setDeathCauseTime(System.currentTimeMillis());
        return (System.currentTimeMillis() - persona.getTimeOfWritingName()) <= CAUSE_TIME;
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null) {
            throw new IllegalStateException("Cause can not be blank");
        }
        if (this.lastName.isBlank()) {
            throw new IllegalStateException("Last name can not be blank");
        }
        Persona persona = this.deathNote.get(this.lastName);
        if ((System.currentTimeMillis() - persona.getDeathCauseTime()) <= DETAILS_TIME) {
            persona.setDeathDetails(details);
        }
        return (System.currentTimeMillis() - persona.getDeathCauseTime()) <= DETAILS_TIME;
    }

    @Override
    public String getDeathCause(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank");
        }
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No " + name + " found");
        }
        return deathNote.get(name).getCauseOfDeath();
    }

    @Override
    public String getDeathDetails(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank");
        }
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No " + name + " found");
        }
        return deathNote.get(name).getDeathDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return deathNote.keySet().contains(name);
    }
}
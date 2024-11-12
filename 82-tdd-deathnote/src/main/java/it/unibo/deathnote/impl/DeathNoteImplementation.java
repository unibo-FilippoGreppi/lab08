package it.unibo.deathnote.impl;

import java.util.Map;
import java.util.HashMap;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.Persona;


public class DeathNoteImplementation implements DeathNote {
    final static private int UNSUPPORTED_RULE = 0;
    final Map<String, Persona> deathNote = new HashMap<>();

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= UNSUPPORTED_RULE) {
            throw new IllegalArgumentException("Index can not be negative or 0");
        }
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
        
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    
}
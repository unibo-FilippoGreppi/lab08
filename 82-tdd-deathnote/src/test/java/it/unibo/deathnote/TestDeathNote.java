package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImplementation;
import it.unibo.deathnote.api.DeathNote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDeathNote {
    private DeathNoteImplementation deathNote;

    @BeforeEach
    void setUp() {
        deathNote = new DeathNoteImplementation();
    }

    @Test
    void testDeathNoteRules() throws IllegalArgumentException {
        try {
            for (int i = -1; i <= DeathNote.RULES.size() + 1; i++) {
                deathNote.getRule(i);
                Assertions.fail("Getting rule , but should have thrown an exception");
            }
        } catch(IllegalArgumentException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
    }

    @Test
    void voidDeathNoteRulesNotEmpty() {
        for (var rule: DeathNote.RULES) {
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testDeath() {
        final String name = "Carlos";
        final String name1 = "Giovanni";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertTrue(deathNote.isNameWritten(name));
        assertFalse(deathNote.isNameWritten(name1));
        deathNote.writeName("");
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testDieWithinTime() throws IllegalStateException, InterruptedException {
        String deathCause = "heart attack";
        try {
            deathNote.writeDeathCause(deathCause);
            Assertions.fail("Death cause has been written, but should have thrown an exception");

        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message        
        }

        final String name3 = "Lidia";
        assertFalse(deathNote.isNameWritten(name3));
        deathNote.writeName(name3);
        assertTrue(deathNote.isNameWritten(name3));
        assertTrue(deathNote.getDeathCause(name3).isBlank());
        deathNote.writeDeathCause(deathCause);
        assertEquals(deathCause.toLowerCase(), deathNote.getDeathCause(name3).toLowerCase());
        final String name4 = "Mathilda";
        assertFalse(deathNote.isNameWritten(name4));
        deathNote.writeName(name4);
        assertTrue(deathNote.isNameWritten(name4));
        assertTrue(deathNote.getDeathCause(name3).isBlank());
        String deathCause2 = "karting accident";
        assertTrue(deathNote.writeDeathCause(deathCause2));       
        assertEquals(deathCause2, deathNote.getDeathCause(name4));
        Thread.sleep(100);
        try {
            deathNote.writeDeathCause("Just another death cause");
            Assertions.fail("Death cause has been changed, but should have thrown an exception");
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message  
        }
        assertEquals(deathCause2, deathNote.getDeathCause(name4));
    }

    @Test
    void testDieWithDetails() {
        String deathCause = "heart attack";
        try {
            deathNote.writeDeathCause(deathCause);
            Assertions.fail("Death cause has been written, but should have thrown an exception");

        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message        
        }
    }


}
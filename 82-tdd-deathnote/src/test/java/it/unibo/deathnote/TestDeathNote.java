package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImplementation;
import it.unibo.deathnote.api.DeathNote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDeathNote {
    private static final long SHORT_TIME = 100;
    private static final long LONG_TIME = 6100;
    private DeathNoteImplementation deathNote;

    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

    @Test
    void testDeathNoteRules() throws IllegalArgumentException {
        try {
            for (int i = -1; i <= DeathNote.RULES.size() + 1; i++) {
                deathNote.getRule(i);
                Assertions.fail("Getting rule, but should have thrown an exception");
            }
        } catch(IllegalArgumentException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
    }

    @Test
    void DeathNoteRulesNotEmpty() {
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
        try {
            deathNote.writeName("");
            Assertions.fail("Writing an empty name, but should have thrown an exception");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testDieWithinTime() throws InterruptedException {
        String deathCause = "heart attack";
        try {
            deathNote.writeDeathCause(deathCause);
            Assertions.fail("Death cause has been written, but should have thrown an exception");
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message        
        } 
        final String name = "Lidia";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertTrue(deathNote.isNameWritten(name));
        assertEquals("heart attack", deathNote.getDeathCause(name));
        deathNote.writeDeathCause(deathCause);
        assertEquals(deathCause.toLowerCase(), deathNote.getDeathCause(name).toLowerCase());
        final String name2 = "Mathilda";
        assertFalse(deathNote.isNameWritten(name2));
        deathNote.writeName(name2);
        assertTrue(deathNote.isNameWritten(name2));
        assertEquals("heart attack", deathNote.getDeathCause(name));
        String deathCause2 = "karting accident";
        assertTrue(deathNote.writeDeathCause(deathCause2));       
        assertEquals(deathCause2, deathNote.getDeathCause(name2));
        Thread.sleep(SHORT_TIME);
        assertFalse(deathNote.writeDeathCause("Just another death cause"));
        assertEquals(deathCause2, deathNote.getDeathCause(name2));
    }

    @Test
    void testDieWithDetails() throws InterruptedException {
        String deathDetails = "ran for too long";
        try {
            deathNote.writeDetails(deathDetails);
            Assertions.fail("Death cause has been written, but should have thrown an exception");
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());// Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message        
        }
        final String name = "Pedro";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertTrue(deathNote.isNameWritten(name));
        assertEquals("", deathNote.getDeathDetails(name));
        assertTrue(deathNote.writeDetails(deathDetails));
        assertEquals(deathDetails, deathNote.getDeathDetails(name));
        final String name2 = "Colin";
        assertFalse(deathNote.isNameWritten(name2));
        deathNote.writeName(name2);
        assertTrue(deathNote.isNameWritten(name2));
        Thread.sleep(LONG_TIME);
        String deathDetails2 = "just another type of details";
        assertFalse(deathNote.writeDetails(deathDetails2));
        assertEquals("", deathNote.getDeathDetails(name2));
    }
}
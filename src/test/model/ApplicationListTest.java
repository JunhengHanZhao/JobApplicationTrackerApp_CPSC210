package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationListTest {
    private ApplicationList testList;

    @BeforeEach
    void runBefore() {
        testList = new ApplicationList();
    }

    @Test
    void testConstructor() {
        assertEquals(0,testList.getApplicationList().size());
    }

    @Test
    void testEditingList() {
        testList.addApplication(new Application(-1,"TestComp1","TestPos1"));
        testList.addApplication(new Application(1,"TestComp2","TestPos2"));
        testList.addApplication(new Application(5,"TestComp3","TestPos3"));
        assertTrue(testList.getApplicationList().containsKey("TestComp1_TestPos1"));
        assertTrue(testList.getApplicationList().containsKey("TestComp2_TestPos2"));
        assertTrue(testList.getApplicationList().containsKey("TestComp3_TestPos3"));
        testList.removeApplication(testList.getApplication("TestComp1", "TestPos1"));
        assertFalse(testList.getApplicationList().containsKey("TestComp1_TestPos1"));
        testList.removeApplication(testList.getApplication("TestComp2", "TestPos2"));
        assertFalse(testList.getApplicationList().containsKey("TestComp2_TestPos2"));
        testList.removeApplication(testList.getApplication("TestComp3", "TestPos3"));
        assertFalse(testList.getApplicationList().containsKey("TestComp3_TestPos3"));
    }

    @Test
    void testUrgent() {
        assertNull(testList.mostUrgentApplication());
        testList.addApplication(new Application(-1,"TestComp1","TestPos1"));
        testList.addApplication(new Application(-1,"TestComp2","TestPos2"));
        assertEquals(testList.getApplication("TestComp1","TestPos1"), testList.mostUrgentApplication());
        testList.addApplication(new Application(5,"TestComp3","TestPos3"));
        testList.addApplication(new Application(3,"TestComp4","TestPos4"));
        assertEquals(testList.getApplication("TestComp4","TestPos4"), testList.mostUrgentApplication());
    }
}

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
        testList.addApplication(-1,"TestComp1","TestPos1");
        testList.addApplication(1,"TestComp2","TestPos2");
        testList.addApplication(5,"TestComp3","TestPos3");
        assertTrue(testList.getApplicationList().containsKey("TestComp1_TestPos1"));
        assertTrue(testList.getApplicationList().containsKey("TestComp2_TestPos2"));
        assertTrue(testList.getApplicationList().containsKey("TestComp3_TestPos3"));
        testList.removeApplication("TestComp1","TestPos1");
        assertFalse(testList.getApplicationList().containsKey("TestComp1_TestPos1"));
        testList.removeApplication("TestComp2","TestPos2");
        assertFalse(testList.getApplicationList().containsKey("TestComp2_TestPos2"));
        testList.removeApplication("TestComp3","TestPos3");
        assertFalse(testList.getApplicationList().containsKey("TestComp3_TestPos3"));
    }
}

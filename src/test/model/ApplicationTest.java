package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    private Application testApplication1;
    private Application testApplication2;
    private Application testApplication3;

    @BeforeEach
    void runBefore() {
        testApplication1 = new Application(-1,"TestComp1","TestPos1");
        testApplication2 = new Application(1,"TestComp2","TestPos2");
        testApplication3 = new Application(20,"TestComp3","TestPos3");
    }

    @Test
    void testConstructor() {
        assertEquals("TestComp1", testApplication1.getCompanyName());
        assertEquals("TestComp2", testApplication2.getCompanyName());
        assertEquals("TestPos1", testApplication1.getPositionName());
        assertEquals("TestPos2", testApplication2.getPositionName());
        assertEquals(-1,testApplication1.getApplicationDeadline());
        assertEquals(1,testApplication2.getApplicationDeadline());
        assertEquals(20,testApplication3.getApplicationDeadline());
        assertEquals(0,testApplication1.getApplicationStatus());
        assertEquals(0,testApplication2.getApplicationStatus());
        assertEquals(0,testApplication3.getApplicationStatus());
    }

    @Test
    void testApplicationStatus() {
        testApplication1.modifyApplicationStatus(1);
        assertEquals(1,testApplication1.getApplicationStatus());
        testApplication2.modifyApplicationStatus(2);
        assertEquals(2,testApplication2.getApplicationStatus());
        testApplication3.modifyApplicationStatus(4);
        assertEquals(4,testApplication3.getApplicationStatus());
    }

    @Test
    void testApplicationDeadline() {
        testApplication1.modifyApplicationDeadline(1);
        assertEquals(1, testApplication1.getApplicationDeadline());
        testApplication2.modifyApplicationDeadline(-1);
        assertEquals(-1, testApplication2.getApplicationDeadline());
        testApplication3.modifyApplicationDeadline(5);
        assertEquals(5, testApplication3.getApplicationDeadline());
    }

//    @Test
//    void testDocuments() {
//        testApplication1.addDocument("CV",true);
//        testApplication2.addDocument("CV",false);
//        testApplication3.addDocument("PS",true);
//        assertTrue(testApplication1.getDocumentList().containsKey("CV"));
//        assertTrue(testApplication2.getDocumentList().containsKey("CV"));
//        assertTrue(testApplication3.getDocumentList().containsKey("PS"));
//        assertTrue(testApplication1.getDocumentList().get("CV"));
//        assertFalse(testApplication2.getDocumentList().get("CV"));
//        assertTrue(testApplication3.getDocumentList().get("PS"));
//        testApplication1.addDocument("PS",true);
//        testApplication2.addDocument("PS",false);
//        testApplication3.addDocument("CV",true);
//        testApplication1.modifyDocumentStatus("PS",false);
//        testApplication2.modifyDocumentStatus("PS",true);
//        testApplication3.modifyDocumentStatus("PS",false);
//        assertFalse(testApplication1.getDocumentList().get("PS"));
//        assertTrue(testApplication2.getDocumentList().get("PS"));
//        assertFalse(testApplication3.getDocumentList().get("PS"));
//        testApplication1.removeDocument("CV");
//        testApplication2.removeDocument("CV");
//        testApplication3.removeDocument("CV");
//        assertFalse(testApplication1.getDocumentList().containsKey("CV"));
//        assertFalse(testApplication2.getDocumentList().containsKey("CV"));
//        assertFalse(testApplication3.getDocumentList().containsKey("CV"));
//    }
}
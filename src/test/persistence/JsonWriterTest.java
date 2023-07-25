package persistence;

import model.Application;
import model.ApplicationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    // Run this writer test before running reader test

    @Test
    void testWriterInvalidFile() {
        try {
            ApplicationList al = new ApplicationList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ApplicationList al = new ApplicationList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyApplicationList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyApplicationList.json");
            al = reader.read();
            assertEquals(0, al.getApplicationList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ApplicationList al = new ApplicationList();
            al.addApplication(new Application(-1,"TestCom1","TestPos1"));
            al.addApplication(new Application(10,"TestCom2","TestPos2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralApplicationList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralApplicationList.json");
            al = reader.read();
            assertEquals(2, al.getApplicationList().size());
            checkApplication("TestCom1","TestPos1",0,-1,
                    al.getApplication("TestCom1","TestPos1"));
            checkApplication("TestCom2","TestPos2",0,10,
                    al.getApplication("TestCom2","TestPos2"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
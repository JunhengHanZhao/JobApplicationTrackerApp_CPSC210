package persistence;

import model.ApplicationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Adapted from JsonSerializationModel
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ApplicationList al = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyApplicationList.json");
        try {
            ApplicationList al = reader.read();
            assertEquals(0, al.getApplicationList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralApplicationList.json");
        try {
            ApplicationList al = reader.read();
            assertEquals(2, al.getApplicationList().size());
            checkApplication("TestCom1","TestPos1",0,-1,
                    al.getApplication("TestCom1","TestPos1"));
            checkApplication("TestCom2","TestPos2",0,10,
                    al.getApplication("TestCom2","TestPos2"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
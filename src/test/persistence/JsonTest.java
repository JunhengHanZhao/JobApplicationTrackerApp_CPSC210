package persistence;

import model.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Adapted from JsonSerializationModel   // Adapted from JsonSerializationModel
public class JsonTest {
    protected void checkApplication(String companyName, String positionName, int applicationStatus,
                               int applicationDeadline, Application application) {
        assertEquals(companyName, application.getCompanyName());
        assertEquals(positionName, application.getPositionName());
        assertEquals(applicationStatus, application.getApplicationStatus());
        assertEquals(applicationDeadline, application.getApplicationDeadline());
    }
}

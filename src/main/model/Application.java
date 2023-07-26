package model;

import org.json.JSONObject;
import persistence.Writable;


//Represent an application having a status, deadline, document list
public class Application implements Writable {
    // The name of the company
    private String companyName;

    // The name of the position
    private String positionName;

    // Using an integer represent status of the application, 0 means not submitted,
    // 1 means waiting for reply, 2 means in process of interview, 3 means rejected,
    // 4 means ghosted.
    private int applicationStatus;

    // Using integer represent number of days left before deadline of the
    // acceptance of application, -1 means deadline is not applicable.
    private int applicationDeadline;

    // REQUIRES: applicationDeadline > 0 or = -1, companyName and positionName has length >0
    // MODIFIES: This
    // EFFECTS: Construct an application
    public Application(int applicationDeadline, String companyName, String positionName) {
        this.companyName = companyName;
        this.positionName = positionName;
        this.applicationStatus = 0;
        this.applicationDeadline = applicationDeadline;
    }

    // REQUIRES: applicationStatus = 0 ~ 4
    // MODIFIES: This
    // EFFECTS: Change the status of this application
    public void modifyApplicationStatus(int applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    // REQUIRES: applicationDeadline > 0 or = -1
    // MODIFIES: This
    // EFFECTS: Change the deadline of this application
    public void modifyApplicationDeadline(int applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    // A bunch on getter methods below here
    public String getCompanyName() {
        return companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public int getApplicationStatus() {
        return applicationStatus;
    }

    public int getApplicationDeadline() {
        return applicationDeadline;
    }

    // Adapted from JsonSerializationModel
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("company name", companyName);
        json.put("position name", positionName);
        json.put("application status", applicationStatus);
        json.put("application deadline", applicationDeadline);
        return json;
    }
}

package model;

import java.util.HashMap;

// represent A list of applications
public class ApplicationList {
    // A list of applications being collected
    private HashMap<String, Application> applicationList;

    // MODIFIES: this
    // EFFECTS: Construct an application list
    public ApplicationList() {
        this.applicationList = new HashMap<String, Application>();
    }

    // REQUIRES: application has to not exist, applicationDeadline > 0 or = -1,
    // companyName and positionName has length >0
    // MODIFIES: This
    // EFFECTS: add an application into application list
    public void addApplication(int deadline, String companyName, String positionName) {
        this.applicationList.put(companyName + "_" + positionName,
                new Application(deadline, companyName, positionName));
    }

    // REQUIRES: application has to exist, companyName and positionName has length >0
    // MODIFIES: This
    // EFFECTS: remove an application from application list
    public void removeApplication(String companyName, String positionName) {
        this.applicationList.remove(companyName + "_" + positionName);
    }

    public HashMap<String, Application> getApplicationList() {
        return applicationList;
    }
}

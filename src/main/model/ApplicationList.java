package model;

import com.sun.jdi.Value;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// represent A list of applications
public class ApplicationList {
    // A list of applications being collected
    private HashMap<String, Application> applicationList;

    // MODIFIES: this
    // EFFECTS: Construct an application list
    public ApplicationList() {
        this.applicationList = new HashMap<String, Application>();
    }

    // EFFECTS: Return the application with shortest deadline
    public Application mostUrgentApplication() {
        int shortestDeadline = 1000000;
        String shortestDeadlineName = "";
        String noDeadlineName = "";
        for (String name: this.applicationList.keySet()) {
            int deadline = this.applicationList.get(name).getApplicationDeadline();
            if (deadline > 0 && deadline < shortestDeadline) {
                shortestDeadline = deadline;
                shortestDeadlineName = name;
            } else if (deadline == -1) {
                noDeadlineName = name;
            }
        }
        if (shortestDeadlineName != "") {
            return  applicationList.get(shortestDeadlineName);
        } else {
            return  applicationList.get(noDeadlineName);
        }
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

    public void removeApplication(Application selectedApplication) {
        this.applicationList.remove(selectedApplication.getCompanyName() + "_"
                + selectedApplication.getPositionName());
    }

    public Application getApplication(String companyName, String positionName) {
        return this.applicationList.get(companyName + "_" + positionName);
    }

    public HashMap<String, Application> getApplicationList() {
        return applicationList;
    }
}

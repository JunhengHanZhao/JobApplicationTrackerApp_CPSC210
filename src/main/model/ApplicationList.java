package model;

import com.sun.jdi.Value;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// represent A list of applications and hold methods for it
public class ApplicationList implements Writable {
    // A list of applications being collected
    private HashMap<String, Application> applicationList;

    // MODIFIES: this
    // EFFECTS: Construct an application list
    public ApplicationList() {
        this.applicationList = new HashMap<String, Application>();
    }

    // EFFECTS: Return the application with the shortest deadline
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
    public void addApplication(Application application) {
        String companyName = application.getCompanyName();
        String positionName = application.getPositionName();
        this.applicationList.put(companyName + "_" + positionName, application);
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

    // Adapted from JsonSerializationModel
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("applications", applicationsToJson());
        return json;
    }

    // EFFECT: returns applications in this application list as a JSON array
    private JSONArray applicationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String name: this.applicationList.keySet()) {
            Application a = this.applicationList.get(name);
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}

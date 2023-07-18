package model;

import java.util.HashMap;

//Represent an application having a status, deadline, document list
public class Application {
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

    // name of documents in a hashmap, name is the key entry and the boolean represents
    // the status of the documents' preparation, true means ready, false means not ready.
    //private HashMap<String, Boolean> documentList;

    // REQUIRES: applicationDeadline > 0 or = -1, companyName and positionName has length >0
    // MODIFIES: This
    // EFFECTS: Construct an application
    public Application(int applicationDeadline, String companyName, String positionName) {
        this.companyName = companyName;
        this.positionName = positionName;
        this.applicationStatus = 0;
        this.applicationDeadline = applicationDeadline;
        //this.documentList = new HashMap<String, Boolean>();
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

    // REQUIRES: file has to not exist, fileName has length >0
    // MODIFIES: This
    // EFFECTS: Add a document into document list
    //public void addDocument(String fileName, boolean fileStatus) {
        //this.documentList.put(fileName, fileStatus);
    //}

    // REQUIRES: file has to exist, fileName has length >0
    // MODIFIES: This
    // EFFECTS: Add a document into document list
    // public void removeDocument(String fileName) {
    //      this.documentList.remove(fileName);
    //}

    // REQUIRES: file has to exist, fileName has length >0
    // MODIFIES: This
    // EFFECTS: Change the status of this file
    // public void modifyDocumentStatus(String fileName, boolean fileStatus) {
    //    this.documentList.replace(fileName, fileStatus);
    // }

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

//    public HashMap<String, Boolean> getDocumentList() {
//        return documentList;
//    }
}

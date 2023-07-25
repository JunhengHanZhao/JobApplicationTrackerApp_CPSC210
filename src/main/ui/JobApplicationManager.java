package ui;

import com.sun.jdi.Value;
import model.Application;
import model.ApplicationList;

import java.security.Key;
import java.util.Scanner;

// Job application manager application
public class JobApplicationManager {

    private Scanner input;
    private ApplicationList list;

    // EFFECTS: runs the application manager app
    public JobApplicationManager() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            viewApplication();
        } else if (command.equals("a")) {
            addApplication();
        } else if (command.equals("d")) {
            deleteApplication();
        } else if (command.equals("e")) {
            editApplication();
        } else if (command.equals("r")) {
            viewUrgent();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        list = new ApplicationList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view applications");
        System.out.println("\ta -> add application");
        System.out.println("\td -> delete application");
        System.out.println("\te -> edit application");
        System.out.println("\tr -> view urgent");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prompts user to select an application in the list
    private Application selectApplication() {
        String selection = "";  // force entry into loop
        String selectCompany = "";
        String selectPos = "";

        viewApplication();

        System.out.println("please select company: ");
        selectCompany = input.next();
        System.out.println("please select position: ");
        selectPos = input.next();
        selection = selectCompany + "_" + selectPos;

        return list.getApplicationList().get(selection);
    }

    // EFFECTS: display all created applications
    private void viewApplication() {
        String applicationStatus = "";
        String applicationDeadline = "";
        for (String name : list.getApplicationList().keySet()) {
            String companyName = list.getApplicationList().get(name).getCompanyName();
            String positionName = list.getApplicationList().get(name).getPositionName();
            int status = list.getApplicationList().get(name).getApplicationStatus();
            applicationStatus = applicationStatus(status);
            int deadline = list.getApplicationList().get(name).getApplicationDeadline();
            if (deadline == -1) {
                applicationDeadline = "Not applicable";
            } else {
                applicationDeadline = deadline + " Days";
            }
            System.out.println("Applied Company: " + companyName + ", Applied Position: " + positionName
                    + ", Application Status: " + applicationStatus + ", Application Deadline: " + applicationDeadline);
        }
    }

    // helper method
    private String applicationStatus(int status) {
        String applicationStatus = "";
        if (status == 0) {
            applicationStatus = "Not Submitted";
        } else if (status == 1) {
            applicationStatus = "Waiting for reply";
        } else if (status == 2) {
            applicationStatus = "Waiting for interview";
        } else if (status == 3) {
            applicationStatus = "Rejected";
        } else if (status == 4) {
            applicationStatus = "Ghosted";
        }
        return applicationStatus;
    }

    // EFFECTS: add user specified application
    // MODIFIES: this
    private void addApplication() {
        String company = "";
        String position = "";
        String hasDeadline = "";
        int deadline = 0;

        System.out.println("does this application has a deadline ? Select from: y/n");
        hasDeadline = input.next();
        if (hasDeadline.equals("y")) {
            System.out.println("please specify days before the deadline");
            deadline = input.nextInt();
        } else if (hasDeadline.equals("n")) {
            deadline = -1;
        } else {
            System.out.println("Selection not valid...");
        }

        System.out.println("What's the name of the company?");
        company = input.next();
        System.out.println("What's the position applying?");
        position = input.next();

        list.addApplication(new Application(deadline, company, position));
    }

    // EFFECTS: delete an existing application
    // MODIFIES: this
    private void deleteApplication() {
        list.removeApplication(selectApplication());
    }

    // EFFECTS: edit an existing application
    // MODIFIES: this
    private void editApplication() {
        Application application = selectApplication();
        String command = "";
        int status = -1;
        int deadline = 0;

        System.out.println("\nSelect from:");
        System.out.println("\ts -> Modify application status");
        System.out.println("\td -> Modify application deadline");

        command = input.next();
        if (command.equals("s")) {
            System.out.println("Please specify the new application status");
            status = input.nextInt();
            application.modifyApplicationStatus(status);
        } else if (command.equals("d")) {
            System.out.println("Please specify the new application deadline");
            deadline = input.nextInt();
            application.modifyApplicationDeadline(deadline);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: display the most urgent application
    private void viewUrgent() {
        Application urgentApplication = list.mostUrgentApplication();
        if (urgentApplication.getApplicationDeadline() >= 0) {
            System.out.println("Most Urgent Application is: " + ", Company: "
                    + urgentApplication.getCompanyName() + ", Position: "
                    + urgentApplication.getPositionName() + ", Deadline: "
                    + urgentApplication.getApplicationDeadline() + " Days");
        } else {
            System.out.println("None of your application has deadline");
        }
    }

}
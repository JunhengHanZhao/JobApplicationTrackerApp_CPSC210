package persistence;

import model.Application;
import model.ApplicationList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Adapted from JsonSerializationModel
// Reader for json files
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads application list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ApplicationList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseApplicationList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses application list from JSON object and returns it
    private ApplicationList parseApplicationList(JSONObject jsonObject) {
        ApplicationList al = new ApplicationList();
        addApplications(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses thingies from JSON object and adds them to application list
    private void addApplications(ApplicationList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("applications");
        for (Object json : jsonArray) {
            JSONObject nextApplication = (JSONObject) json;
            addApplication(al, nextApplication);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses thingy from JSON object and adds it to application list
    private void addApplication(ApplicationList al, JSONObject jsonObject) {
        String companyName = jsonObject.getString("company name");
        String positionName = jsonObject.getString("position name");
        int deadline = jsonObject.getInt("application deadline");
        int status = jsonObject.getInt("application status");
        Application application = new Application(deadline,companyName,positionName);
        application.modifyApplicationStatus(status);
        al.addApplication(application);
    }
}

package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonFileUtils<T> {

    public void createJsonWithName(String fileName, List<T> items) throws IOException, InterruptedException {
        String jsonFilePath = "src/main/resources/" + fileName;
        createJsonFile(jsonFilePath, items);
        System.out.println("JSON file is successfully created. JSON filepath: " + jsonFilePath);
    }

    public void createJsonFile(String jsonFilePath, List<T> items) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String outputString = gson.toJson(items);
        try (FileWriter output = new FileWriter(jsonFilePath)) {
            output.write(outputString);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
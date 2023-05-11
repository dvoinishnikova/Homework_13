package homework13.todos;

import com.google.gson.Gson;
import utils.JsonFileUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ToDo {

    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public void createJsonWithOpenTasks(int userId) throws IOException, InterruptedException {
        JsonFileUtils<Task> jsonFileUtils = new JsonFileUtils<>();
        String fileName = String.format("user-%s-open-tasks.json", userId);
        jsonFileUtils.createJsonWithName(fileName, getOpenTasks(userId));
    }

    public List<Task> getOpenTasks(int userId) throws IOException, InterruptedException {
        Task[] result = getAllUsersTasks(userId);
        return Arrays.stream(result)
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public Task[] getAllUsersTasks(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId + "/todos"))
                .GET()
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), Task[].class);
    }

}
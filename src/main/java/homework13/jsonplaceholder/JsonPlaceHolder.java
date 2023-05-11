package homework13.jsonplaceholder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import utils.FilePathUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

@Slf4j
public class JsonPlaceHolder {

    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String NEW_USER_FILE = "newUser.json";
    private static final String UPDATE_USER_FILE = "updateUser.json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final Gson GSON = new Gson();
    public static final String JSON = "application/json; charset=UTF-8";
    public static final String STATUS_CODE = "Status code - [{}]";

    public User createUser() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users"))
                .header(CONTENT_TYPE, JSON)
                .POST(HttpRequest.BodyPublishers
                        .ofFile(FilePathUtils
                                .getFilePath(NEW_USER_FILE)))
                .build();
        log.info(STATUS_CODE, getStatusCode(request));
        return getResponse(request);
    }

    public User updateUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .PUT(HttpRequest.BodyPublishers
                        .ofFile(FilePathUtils
                                .getFilePath(UPDATE_USER_FILE)))
                .build();
        log.info(STATUS_CODE, getStatusCode(request));
        return getResponse(request);
    }

    public int deleteUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .DELETE()
                .build();
        return getStatusCode(request);
    }

    public User getResponse(HttpRequest request) throws IOException, InterruptedException {
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public Collection<User> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users"))
                .GET()
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(STATUS_CODE, getStatusCode(request));
        Type collectionType = new TypeToken<Collection<User>>(){}.getType();
        return GSON.fromJson(response.body(), collectionType);
    }

    public User getUserById(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .GET()
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(STATUS_CODE, getStatusCode(request));
        return GSON.fromJson(response.body(), User.class);
    }

    public List<User> getUserByUserName(String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users?username=" + userName))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(STATUS_CODE, getStatusCode(request));
        Type listType = new TypeToken<List<User>>(){}.getType();
        return GSON.fromJson(response.body(), listType);
    }

    public int getStatusCode(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

}
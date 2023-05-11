package homework13;

import lombok.extern.slf4j.Slf4j;
import homework13.jsonplaceholder.JsonPlaceHolder;
import homework13.posts.UserComment;
import homework13.todos.ToDo;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException  {
        JsonPlaceHolder jsonPlaceholder = new JsonPlaceHolder();
        UserComment userComment = new UserComment();
        ToDo toDo = new ToDo();

        log.info("CREATE USER");
        log.info("Created User: [{}]", jsonPlaceholder.createUser());


        log.info("UPDATE USER");
        log.info("Updated User: [{}]", jsonPlaceholder.updateUser(10));

        log.info("DELETE USER");
        log.info("Status code: [{}]", jsonPlaceholder.deleteUser(8));

        log.info("GET ALL USERS LIST");
        log.info("Get all Users list: [{}]", jsonPlaceholder.getAllUsers());

        log.info("GET USER BY ID");
        log.info("Get User by Id: [{}]", jsonPlaceholder.getUserById(7));

        log.info("GET USER BY USER NAME");
        log.info("Get User by UserName: [{}]", jsonPlaceholder.getUserByUserName("Bret"));
        jsonPlaceholder.getUserByUserName("Bret");

        log.info("CREATE JSON WITH COMMENTS FOT LAST POST");
        userComment.createJsonWithAllCommentsFromLastPostByUserId(5);
        log.info("CREATE JSON WITH OPEN TASKS");
        toDo.createJsonWithOpenTasks(5);
    }
}
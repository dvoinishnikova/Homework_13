package homework13.todos;

import lombok.Getter;

@Getter
public class Task {

    int userId;
    int id;
    String title;
    boolean completed;

    public Task(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

}
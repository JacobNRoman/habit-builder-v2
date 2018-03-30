package habit.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Column(name="task_id")
    @GeneratedValue
    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int goal;

    @OneToMany
    @JoinColumn(name="session_id")
    private List<habit.models.TaskSession> sessions = new ArrayList<>();

    private boolean isAccomplished;

    @ManyToOne
    private User user;

    public Task(){}

    public Task(String name){
        this();
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public List<TaskSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TaskSession> sessions) {
        this.sessions = sessions;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

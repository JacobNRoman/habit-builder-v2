package habit.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TaskSession {

    @GeneratedValue
    @Id
    private int id;
    private LocalTime start;
    private LocalTime stop;
    private long length;
    String lengthToString;
    private LocalDate date;

    @ManyToOne
    private Task task;

    public TaskSession(){}

    // Called to start clock running on a session, sets date and owning task at this time.
    public void startClock(Task task){
        this.setStart(LocalTime.now());
        this.setDate(LocalDate.now());
        this.setTask(task);
    }

    // Called to stop clock running on a session, sets duration as well.
    public void stopClock(){
        this.setStop(LocalTime.now());
        Duration fullLength = Duration.between(this.getStart(), this.getStop());
        this.length = fullLength.getSeconds();
        long minutes = this.length / 60;
        long seconds = this.length % 60;
        this.lengthToString = String.format("%d minutes, %d seconds", minutes, seconds);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getLengthToString() {
        return lengthToString;
    }

    public void setLengthToString(String lengthToString) {
        this.lengthToString = lengthToString;
    }
}

package habit.util;

import com.google.gson.Gson;
import habit.models.Task;
import habit.models.TaskSession;
import habit.models.TaskSessionDao;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class ProgressDataset {

    public static String getProgressDataset(ArrayList<Task> tasks, TaskSessionDao sessionDao){
        // getProgressDataset is a helper method that finds, queries, and sorts the data needed for the progress page
        // it returns the data in the form of a json object
        // dataSets is a list that will hold the sets of time/date pairs associated with each task
        // the dates will be strings formatted to ISO 8601. Structure: HashMap<String taskname, Hashmap<length, date>>
        HashMap<String, HashMap<String, Long>> dataSets = new HashMap<>();
        ListIterator litr = tasks.listIterator();
        //iterate over a list of all the user's tasks
        while (litr.hasNext()){
            Task i = (Task) litr.next();
            String taskName = i.getName();
            HashMap<String, Long> timeDates = new HashMap<>(); //this will contain the final dataset
            //iterate over the list of sessions
            ArrayList<TaskSession> sessions = new ArrayList<>(sessionDao.findAllByTask(i));
            ListIterator sitr = sessions.listIterator();
            while (sitr.hasNext()){
                TaskSession j = (TaskSession) sitr.next();
                String date = j.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                //check to see if date already exists in timedates hashmap. If so, update the length.
                if (timeDates.containsKey(date)){
                    timeDates.put(date, timeDates.get(date) + j.getLength());
                } else {
                    //create the datetime pair
                    Long time = j.getLength(); //TODO - this is currently in seconds. That isn't ideal.
                    timeDates.put(date, time);
                }
            }
            dataSets.put(taskName, timeDates);
        }
        Gson gson = new Gson();
        String jsonDataset = gson.toJson(dataSets);
        return jsonDataset;

    }

}

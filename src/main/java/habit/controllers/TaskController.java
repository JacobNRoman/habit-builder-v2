package habit.controllers;

import com.google.gson.Gson;
import habit.models.Task;
import habit.models.TaskSession;
import habit.models.User;
import habit.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class TaskController {

    @Autowired
    habit.models.TaskDao taskDao;

    @Autowired
    habit.models.TaskSessionDao sessionDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value="")
    public String index(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userDao.findByEmail(currentPrincipalName);

        model.addAttribute("title", "Tasks");
        model.addAttribute("tasks", taskDao.findAllByUser(currentUser));

        return "index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "New Task");
        model.addAttribute(new habit.models.Task());

        return "add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid Task task, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "New Task");

            return "add";
        }
        /*
        Below is a quick and possibly hacky way to get the current logged in user's credentials.
        Once i get the current user's email I query the database for their full info. I think
        it might be possible to do this without the query by adding more info to the security context
        but I don't know if that is a good idea.
        TODO - this snippet is not DRY. I used it three times on this page, need to refactor
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User taskOwner = userDao.findByEmail(currentPrincipalName);
        task.setUser(taskOwner);
        taskDao.save(task);
        return "redirect:";
    }

    @RequestMapping(value="task/{id}", method=RequestMethod.GET)
    public String task(Model model, @PathVariable int id){

        Task task = taskDao.findById(id).get();

        model.addAttribute("title", task.getName());
        model.addAttribute("task", task);

        return "task";
    }

    @RequestMapping(value="task/{id}", method=RequestMethod.POST)
    public String startTask(Model model, @PathVariable int id){

        Task task = taskDao.findById(id).get();
        TaskSession session = new TaskSession();
        session.startClock(task);
        sessionDao.save(session);

        model.addAttribute("task", task);
        model.addAttribute("title", task.getName());
        model.addAttribute("taskSession", session);

        return "task";
    }

    @RequestMapping(value="task/{taskId}/{sessionId}", method=RequestMethod.POST)
    public String endTask(Model model, @PathVariable int taskId, @PathVariable int sessionId){
        Task task  = taskDao.findById(taskId).get();
        TaskSession session = sessionDao.findById(sessionId).get();
        session.stopClock();
        sessionDao.save(session);

        model.addAttribute("task", task);
        model.addAttribute("title", task.getName());

        return "task";
    }

    @RequestMapping(value="progress")
    public String progress(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userDao.findByEmail(currentPrincipalName);

        /*
        TODO - create datasets to pass to chart.js
        Step 1 - find all tasks belonging to current user
        Step 2 - per task, create a hashset of time/date pairs for each session
        Step 3 - wrap up datasets and pass to chart using json
         */

        ArrayList<Task> tasks = new ArrayList<>(taskDao.findAllByUser(currentUser));
        // dataSets is a list that will hold the sets of time/date pairs associated with each task
        // the dates will be strings formatted to ISO 8601. Structure: HashMap<String taskname, Hashmap<length, date>>
        HashMap<String, HashMap<String, Long>> dataSets = new HashMap<>();
        ListIterator litr = tasks.listIterator();
        //iterate over a list of all the user's tasks
        while (litr.hasNext()){
            Task i = (Task) litr.next();
            String taskName = i.getName();
            ArrayList<TaskSession> sessions = new ArrayList<>(sessionDao.findAllByTask(i));
            HashMap<String, Long> timeDates = new HashMap<>(); //this will contain the final dataset
            //iterate over the list of sessions
            ListIterator sitr = sessions.listIterator();
            while (sitr.hasNext()){
                TaskSession j = (TaskSession) sitr.next();
                String date = j.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                //check to see if date already exists in timedates hashmap
                if (timeDates.containsKey(date)){
                    timeDates.put(date, timeDates.get(date) + j.getLength());
                } else {
                    Long time = j.getLength(); //TODO - this is currently in seconds. That isn't ideal.
                    timeDates.put(date, time);
                }
            }
            dataSets.put(taskName, timeDates);
        }
        Gson gson = new Gson();
        String jsonDataset = gson.toJson(dataSets);

        model.addAttribute("title", "My Progress");
        model.addAttribute("taskSessions", sessionDao.findAllByTaskUserOrderByIdDesc(currentUser));
        model.addAttribute("datasets", jsonDataset);

        return "progress";
    }
}

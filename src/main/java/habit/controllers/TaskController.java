package habit.controllers;

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

        model.addAttribute("title", "My Progress");
        model.addAttribute("taskSessions", sessionDao.findAllByTaskUserOrderByIdDesc(currentUser));

        return "progress";
    }
}

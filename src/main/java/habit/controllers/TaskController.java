package habit.controllers;

import habit.models.Task;
import habit.models.TaskSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    habit.models.TaskDao taskDao;

    @Autowired
    habit.models.TaskSessionDao sessionDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Tasks");
        model.addAttribute("tasks", taskDao.findAll());

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

        model.addAttribute("title", "My Progress");
        model.addAttribute("taskSessions", sessionDao.findAllByOrderByIdDesc());

        return "progress";
    }
}

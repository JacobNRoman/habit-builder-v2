package habit.controllers;

import habit.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistrationForm(
            Model model, @ModelAttribute("user") @Valid UserDto accountDto, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("user", new UserDto());
            return "registration";
        }
        else {
            User registered = new User(accountDto);
            registered.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            UserRole registeredUserRole = new UserRole(registered, "ROLE_USER");
            userDao.save(registered);
            userRoleDao.save(registeredUserRole);
        }
        return "home";
    }
}

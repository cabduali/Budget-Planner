package com.BudgetPlanner.controller;
import com.BudgetPlanner.DTO.WebUser;
import com.BudgetPlanner.entity.AppUser;
import com.BudgetPlanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }
// waxuu soo bandigaa in la arko registration
    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){
        model.addAttribute("webUser", new WebUser());
        return "registration-page";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("webUser") WebUser webUser, BindingResult result, Model model){
        String username = webUser.getUsername();
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registration-page";
        }
        AppUser existingUser = userService.findUserByUserName(username);
        if (existingUser != null) {
            model.addAttribute("webUser", new WebUser());
            return "redirect:/showRegistrationForm?userFound";
        }
        userService.save(webUser);
        return "redirect:/showLoginPage?abaas";
    }
}

package petroom.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import petroom.system.Login;

import javax.validation.Valid;

@Controller
public class UserController {
    private static final String VIEWS_USER_CREATE_FORM = "signUp";
    private final UserRepository users;

    public UserController(UserRepository users)
    {
        this.users = users;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return VIEWS_USER_CREATE_FORM;
    }

    @PostMapping("/signUp")
    public String signUpSubmit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_FORM;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.users.save(user);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage(Model model){
        Login login = new Login();
        model.addAttribute("login", login);
            return "login";
    }

    @GetMapping("/homeSignedIn")
    public String loggedInPage(){
        return "homeSignedIn";
    }

    @GetMapping("/login?error")
    public String loginError(){
        return "login?error";
    }

    @GetMapping("/signOut")
    public String signOutPage(){
        return "signOut";
    }

}

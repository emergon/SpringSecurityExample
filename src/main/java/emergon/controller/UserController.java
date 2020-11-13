package emergon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")//We write here the URL that we want our methods to inherit
public class UserController {
    
    @GetMapping
    public String showUserPage(){
        return "user/userHome";//ViewResolver will create WEB-INF/view/user/userHome.jsp
    }
    
}

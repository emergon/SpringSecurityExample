
package emergon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
    
    @GetMapping("/loginPage")
    public String showLoginPage(){
        return "login";
    }
    
    @RequestMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }
   
}

package emergon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")//We write here the URL that we want our methods to inherit
public class AdminController {
    
    @GetMapping
    public String showAdminPage(){
        return "admin/adminHome";//WEB-INF/view/admin/adminHome.jsp
    }
    
}

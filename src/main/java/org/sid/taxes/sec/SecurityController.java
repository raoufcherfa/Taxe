package org.sid.taxes.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/")
        public String home(){
            return"redirect:/entreprises";
        }

    @RequestMapping("/403")
    public String accessDenied(){
        return "403";
    }
}

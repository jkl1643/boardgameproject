package Custom_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomController {

    @RequestMapping("/custom")
    public String handleStep1() {
        return "newaccount";
    }


}


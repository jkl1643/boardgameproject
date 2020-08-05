package Custom_Controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class CustomController {

	@RequestMapping("/custom")
	public String handleStep1() {
		return "custom/customlist";
	}
	
	
}

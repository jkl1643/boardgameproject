package Custom_Controller;


import org.springframework.stereotype.Controller;

import custom_asking.CustomDao;

@Controller
public class CustomChangeController {
	private CustomDao customdao;

	public void setBoardDao(CustomDao customdao) {
		this.customdao =customdao;
	}
}

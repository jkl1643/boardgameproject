package Custom_Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import custom_asking.CustomDao;
import custom_asking.Custom;

@Controller
public class CustomListController {
	private CustomDao customdao;

	public void setBoardDao(CustomDao customdao) {
		this.customdao = customdao;
	}
	
	@GetMapping(value = "/customboard")
	public String list(Model model) {
		List<Custom> customlist = customdao.selectAll();
		model.addAttribute("CustomList",customlist);
		return "/board/freeboard";
	}
}

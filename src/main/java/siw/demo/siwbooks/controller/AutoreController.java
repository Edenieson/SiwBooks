package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import siw.demo.siwbooks.service.AutoreService;

@Controller
public class AutoreController {
	
	@Autowired
	private AutoreService autoreServ;
	
	@GetMapping("/Autori")
	public String getAllAutore(Model model) {
		model.addAttribute("libri", autoreServ.getAllAutore());
		return "autori.html";
	}
}

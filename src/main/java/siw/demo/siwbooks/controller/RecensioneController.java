package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import siw.demo.siwbooks.service.RecensioneService;

@Controller
public class RecensioneController {
	
	@Autowired
	private RecensioneService recensioneServ;
	 //Stampa tutte le recensioni a prescindere dal libro
	
	@GetMapping("/recensioni")
	public String listaRecensioni(Model model) {
		model.addAttribute("recensioni", recensioneServ.getAllRecensioni());
		return "recensioni.html";
	}
	
	
}

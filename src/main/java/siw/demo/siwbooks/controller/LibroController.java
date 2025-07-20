package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import siw.demo.siwbooks.service.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroServ;
	
	@GetMapping("/")
	public String homePage() {
		return "homepage.html";
	}
	
	@GetMapping("/libri")
	public String libri(Model model) {
		model.addAttribute("libro", libroServ.getAllLibri());
		return "libri.html";
	}
	
	@GetMapping("/libri/{id}")
	public String libro(@PathVariable("id") Long id, Model model) {
		model.addAttribute("libro", libroServ.getLibroById(id));
		return "libro.html";
	}
}

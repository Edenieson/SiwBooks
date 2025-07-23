package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import siw.demo.siwbooks.model.Libro;
import siw.demo.siwbooks.model.Recensione;
import siw.demo.siwbooks.service.AutoreService;
import siw.demo.siwbooks.service.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroServ;
	@Autowired
	private AutoreService autoreServ;
	

	
	@GetMapping("/libri")
	public String libri(Model model) {
		model.addAttribute("libri", libroServ.getAllLibri());
		return "libri.html";
	}
	
	@GetMapping("/libri/{id}")
	public String libro(@PathVariable("id") Long id, Model model) {
		model.addAttribute("libro", this.libroServ.getLibroById(id));
		return "libro.html";
	}
	
	@GetMapping("/newBook")
	public String formNuovoLibro(Model model) {
		model.addAttribute("newLibro", new Libro());
		model.addAttribute("lista_autori", this.autoreServ.getAllAutore());
		return "/admin/formNuovoLibro.html";
	}
	
	@PostMapping("/libri")
	public String nuovoLibro(@ModelAttribute("newLibro") Libro libro, Model model) {
		this.libroServ.save(libro);
		model.addAttribute("libro", libro);
		return "redirect:/libri/" + libro.getId();
	}
	
	/*Per le recensioni*/
	@GetMapping("/libri/{id}/formNewRecensione")
	public String formNuovaRecensione(@PathVariable("id") Long id, Model model) {
		Libro libro = this.libroServ.getLibroById(id);
		if(libro == null) return "libroNonTrovato.html";
		if(!model.containsAttribute("recensione")) {
			model.addAttribute("libro", libro);
			model.addAttribute("recensione", new Recensione());
		}
		return "formNuovaRecensione.html";
	}
}

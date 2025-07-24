package siw.demo.siwbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import siw.demo.siwbooks.model.Credenziali;
import siw.demo.siwbooks.model.Libro;
import siw.demo.siwbooks.model.Recensione;
import siw.demo.siwbooks.model.Utente;
import siw.demo.siwbooks.service.AutoreService;
import siw.demo.siwbooks.service.CredenzialiService;
import siw.demo.siwbooks.service.LibroService;
import siw.demo.siwbooks.service.RecensioneService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroServ;
	@Autowired
	private AutoreService autoreServ;
	@Autowired
	private CredenzialiService credenzialiServ;
	@Autowired
	private RecensioneService recensioneServ;

	
	@GetMapping("/libri")
	public String libri(Model model) {
		model.addAttribute("libri", libroServ.getAllLibri());
		return "libri.html";
	}
	
	@GetMapping("/libri/{id}")
	public String libro(@PathVariable("id") Long id, Model model) {
		Libro libro = libroServ.getLibroById(id);
		List<Recensione> recensioni = libro.getRecensioni();
		model.addAttribute("libro", libro);
		model.addAttribute("recensioni", recensioni);
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
	
	/*Per eliminare i libri*/
	@GetMapping("/admin/deleteLibri")
	public String gestioneLibri(Model model) {
		Iterable<Libro> libri = libroServ.getAllLibri();
		model.addAttribute("libri", libri);
		return "/admin/eliminaLibro.html";
	}
	
	@PostMapping("/admin/deleteLibri/delete")
	public String eliminaLibri(@RequestParam(name = "libriDaEliminare", required = false) List<Long> libriDaEliminare) {
	    if (libriDaEliminare != null) {
	        for (Long id : libriDaEliminare) {
	            libroServ.deleteById(id);
	        }
	    }
	    return "redirect:/admin/deleteLibri";
	}

	
	
	
	/*Per le recensioni*/
	
	@GetMapping("/libri/{id}/formNuovaRecensione")
	public String formNewRecensione(@PathVariable("id") Long id , Model model) {
		Libro libro = this.libroServ.getLibroById(id);
		if(libro==null) { 
			return "libroNonTrovato.html"; //aggiungere errore
		}
		if(!model.containsAttribute("recensione")) {
			model.addAttribute("libro", libro);
			model.addAttribute("recensione",new Recensione());
		}
		return "formNuovaRecensione.html";
	}
	
	@PostMapping("/libri/{id}/recensione")
	public String addRecensione(@PathVariable("id") Long id,
	                            @Valid @ModelAttribute("recensione") Recensione recensione,
	                            BindingResult bindingResult,
	                            RedirectAttributes redirectAttributes,
	                            Model model,
	                            @AuthenticationPrincipal UserDetails userDetails) {

	    Libro libro = this.libroServ.getLibroById(id);
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();

	    // ✅ Recupera l'utente (User) dal sistema
	    Credenziali credenziali = this.credenzialiServ.getCredenziali(username);
	    Utente autore = credenziali.getUtente();

	    if (bindingResult.hasErrors()) {
	        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recensione", bindingResult);
	        redirectAttributes.addFlashAttribute("recensione", recensione);
	        redirectAttributes.addFlashAttribute("libro", libro);
	        return "redirect:/libro/" + libro.getId() + "/formNuovaRecensione";
	    }

	    if (recensioneServ.existsByLibroAndAutore(libro, autore)) {
	        model.addAttribute("libro", libro);
	        model.addAttribute("errorMessage", "Hai già scritto una recensione per questo libro!");
	        return "formNewRecensione.html";
	    }

	    recensione.setId(null);
	    recensione.setLibro(libro);
	    recensione.setAutore(autore); // 👈 ora autore è un User
	    this.recensioneServ.save(recensione);

	    return "redirect:/libri/" + libro.getId();
	}

}

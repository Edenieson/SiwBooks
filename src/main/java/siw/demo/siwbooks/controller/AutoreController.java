package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import siw.demo.siwbooks.model.Autore;
import siw.demo.siwbooks.service.AutoreService;

@Controller
public class AutoreController {

    @Autowired
    private AutoreService autoreServ;

    @GetMapping("/autori")
    public String getAllAutore(Model model) {
        model.addAttribute("autori", autoreServ.getAllAutore());
        return "autori.html";
    }

    @GetMapping("/autori/{id}")
    public String getAutoreById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("autore", autoreServ.getAutoreById(id));
        return "autore.html";
    }

    @GetMapping("/newAuthor")
    public String newAuthor(Model model) {
        model.addAttribute("autore", new Autore()); // nome coerente!
        return "admin/formNuovoAutore.html";
    }

    @PostMapping("/autori")
    public String nuovoAutore(@ModelAttribute("autore") Autore autore) {
        this.autoreServ.save(autore);
        return "redirect:/autori/" + autore.getId();
    }
    
    /*Per eliminare autori*/
    @GetMapping("/admin/deleteAutore")
    public String gestioneAutori(Model model) {
    	Iterable<Autore> autori = autoreServ.getAllAutore();
    	model.addAttribute("autori", autori);
    	return "/admin/eliminaAutore.html";
    }
    
    @PostMapping("/admin/deleteAutore/delete")
    public String eliminaAutori(@RequestParam(name = "autoriDaEliminare", required = false) Iterable<Long> autoriDaEliminare) {
    	if(autoriDaEliminare != null) {
    		for(Long id : autoriDaEliminare) {
    			autoreServ.deleteById(id);
    		}
    	}
    	return "redirect:/admin/deleteAutore";
    }
}


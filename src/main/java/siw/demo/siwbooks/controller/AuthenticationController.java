package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import siw.demo.siwbooks.model.Credenziali;
import siw.demo.siwbooks.model.Utente;
import siw.demo.siwbooks.service.CredenzialiService;
import siw.demo.siwbooks.service.UtenteService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiServ;
	
	@Autowired
	private UtenteService utenteServ;
	
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "formLogin.html";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken) {
			return "homepage.html";
		}
		else {
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credenziali credenziali = credenzialiServ.getCredenziali(userDetails.getUsername());
			if(credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
				return "admin/homepageAdmin.html";
			}
		}
		return "homepage.html";
	}
	
	@GetMapping("/success")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credenziali credenziali = credenzialiServ.getCredenziali(userDetails.getUsername());
		if(credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
			return "admin/homepageAdmin.html";
		}
		return "homepage.html";
	}
	
	@GetMapping("/registrazione")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "formRegistrazione.html";
	}
	
	@PostMapping("/registrazione")
	public String registraUtente(
			@Valid @ModelAttribute("utente") Utente utente, BindingResult utenteBindingResult,
			@Valid @ModelAttribute("credenziali") Credenziali credenziali, BindingResult credenzialiBindingResult,
			Model model
			) {
		
		if(!utenteBindingResult.hasErrors() && !credenzialiBindingResult.hasErrors()) {
			utenteServ.saveUser(utente);
			credenziali.setUtente(utente);
			credenzialiServ.saveCredenziali(credenziali);
			model.addAttribute("utente", utente);
			return "registrazioneEseguita.html";
		}
		return "formRegistrazione.html";
	}
	
}

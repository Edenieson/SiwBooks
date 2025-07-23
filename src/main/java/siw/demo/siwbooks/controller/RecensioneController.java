package siw.demo.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import siw.demo.siwbooks.service.RecensioneService;

@Controller
public class RecensioneController {
	
	@Autowired
	private RecensioneService recensioneServ;
	
	
}

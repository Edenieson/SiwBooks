package siw.demo.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siw.demo.siwbooks.model.Utente;
import siw.demo.siwbooks.repository.UtenteRepository;

@Service
public class UtenteService {
	
		@Autowired
		private UtenteRepository utenteRepo;
		
		public Utente getUser(Long id) {
			return this.utenteRepo.findById(id).get();
		}
		
		public Utente saveUser(Utente utente) {
			return this.utenteRepo.save(utente);
		}
}

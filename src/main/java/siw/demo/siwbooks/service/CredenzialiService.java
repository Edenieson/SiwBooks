package siw.demo.siwbooks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import siw.demo.siwbooks.model.Credenziali;
import siw.demo.siwbooks.repository.CredenzialiRepository;

@Service
public class CredenzialiService {
	
	@Autowired
	protected CredenzialiRepository credenzialiRepo;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Transactional
	public Credenziali getCredenziali(Long id) {
		Optional<Credenziali> result = this.credenzialiRepo.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public Credenziali getCredenziali(String username) {
		Optional<Credenziali> result = credenzialiRepo.findByUsername(username);
		return result.orElse(null);
	}
	
	@Transactional
	public Credenziali saveCredenziali(Credenziali credenziali) {
		credenziali.setRole(Credenziali.DEFAULT_ROLE);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepo.save(credenziali);
	}
}

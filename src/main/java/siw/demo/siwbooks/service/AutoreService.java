package siw.demo.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siw.demo.siwbooks.model.Autore;
import siw.demo.siwbooks.repository.AutoreRepository;

@Service
public class AutoreService {
	
	@Autowired
	private AutoreRepository autoreRepo;
	
	public Autore getAutoreById(Long id) {
		return autoreRepo.findById(id).get();
	}
	
	public Iterable<Autore> getAllAutore(){
		return autoreRepo.findAll();
	}
	
	public Autore save(Autore autore) {
		return autoreRepo.save(autore);
	}
	
	public void deleteById(Long id) {
		autoreRepo.deleteById(id);
	}
}

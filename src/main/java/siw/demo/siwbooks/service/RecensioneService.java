package siw.demo.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siw.demo.siwbooks.model.Libro;
import siw.demo.siwbooks.model.Recensione;
import siw.demo.siwbooks.model.Utente;
import siw.demo.siwbooks.repository.RecensioneRepository;

@Service
public class RecensioneService {
	
	@Autowired
	private RecensioneRepository recensioneRepo;
	
	public Recensione getRecensioneById(Long id) {
		return this.recensioneRepo.findById(id).get();
	}
	
	public Iterable<Recensione> getAllRecensioni(){
		return this.recensioneRepo.findAll();
	}
	
	public Iterable<Recensione> getRecensioneByLibro(Libro libro){
		return this.recensioneRepo.findByLibro(libro);
	}
	
	public Recensione getRecensioneByUtente(Utente utente) {
		return this.recensioneRepo.findByUtente(utente);
	}
	
	public boolean existsByLibroAndAutore(Libro libro, Utente autore) {
	    return this.recensioneRepo.existsByLibroAndAutore(libro, autore);
	}
	
	public void save(Recensione recensione) {
		this.recensioneRepo.save(recensione);
	}
	
}

package siw.demo.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siw.demo.siwbooks.model.Libro;
import siw.demo.siwbooks.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepo;
	
	public Libro getLibroById(Long id) {
		return libroRepo.findById(id).get();
	}
	
	public Iterable<Libro> getAllLibri(){
		return libroRepo.findAll();
	}
	
	public Libro save(Libro libro) {
		return libroRepo.save(libro);
	}
}

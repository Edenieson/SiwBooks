package siw.demo.siwbooks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siw.demo.siwbooks.model.Libro;
import siw.demo.siwbooks.model.Recensione;
import siw.demo.siwbooks.model.Utente;

public interface RecensioneRepository extends CrudRepository<Recensione, Long>{
	
	List<Recensione> findByLibro(Libro libro); //Trova la lista di recensioni di un determinato libro
	
	Recensione findByUtente(Utente utente); //Ogni utente puo' scrivere solo una recensione
}

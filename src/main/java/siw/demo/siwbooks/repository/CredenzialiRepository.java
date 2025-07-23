package siw.demo.siwbooks.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import siw.demo.siwbooks.model.Credenziali;

public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{
	
	public Optional<Credenziali> findByUsername(String username);
		
	
}

package siw.demo.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;

import siw.demo.siwbooks.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long>{

}


package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Azienda;

public interface AziendaRepository extends CrudRepository<Azienda, Long> {
	//public List<Azienda> findByYear(Integer year);
	
	//public boolean existsByTitleAndYear(String title,Integer year);
	
}


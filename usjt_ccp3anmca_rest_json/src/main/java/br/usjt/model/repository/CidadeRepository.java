package br.usjt.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.usjt.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	public Cidade findOneByLatitudeAndLongitude(Double latitude, Double longitude);
	
	@Query("Select c from Cidade c where c.nome like :cidade%")
    public List<Cidade> findByCidadeStartsWith(String cidade);
	
}
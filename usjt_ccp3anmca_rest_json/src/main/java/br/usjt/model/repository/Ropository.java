package br.usjt.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.model.beans.Livro;

public class Ropository {
	public interface LivroRepository extends JpaRepository <Livro, Long>{
	}
	
}

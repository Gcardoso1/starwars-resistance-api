package br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}

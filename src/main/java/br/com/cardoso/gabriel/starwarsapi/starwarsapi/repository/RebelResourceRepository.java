package br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.RebelResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RebelResourceRepository extends JpaRepository<RebelResource, Integer> {
}

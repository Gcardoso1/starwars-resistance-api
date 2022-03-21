package br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}

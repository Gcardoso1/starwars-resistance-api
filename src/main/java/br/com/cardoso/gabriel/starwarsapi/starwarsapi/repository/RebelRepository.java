package br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RebelRepository extends JpaRepository<Rebel, Integer> {
    List<Rebel> findAllByBetrayerIsTrue();
    List<Rebel> findAllByBetrayerIsFalse();
}

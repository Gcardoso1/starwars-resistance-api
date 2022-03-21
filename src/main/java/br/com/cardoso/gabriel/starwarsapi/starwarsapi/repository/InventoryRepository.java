package br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}

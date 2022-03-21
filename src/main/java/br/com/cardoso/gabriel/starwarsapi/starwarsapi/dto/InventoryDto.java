package br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.RebelResource;

import java.util.List;

public record InventoryDto(
    List<RebelResource> resources
) {}

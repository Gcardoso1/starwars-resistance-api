package br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.ResourcesEnum;

public record NegotiationItemDto(
        ResourcesEnum resource,
        int quantity
) {}
package br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto;

import java.util.List;

public record NegotiationDto(
        List<NegotiationItemDto> send,
        List<NegotiationItemDto> receive
) {
}

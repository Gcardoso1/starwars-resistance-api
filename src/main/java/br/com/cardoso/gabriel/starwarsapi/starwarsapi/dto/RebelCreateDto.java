package br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Gender;
import com.sun.istack.NotNull;

import java.util.List;

public record RebelCreateDto(
        @NotNull
        String name,
        @NotNull
        Integer age,
        @NotNull
        Gender gender,
        @NotNull
        LocationDto location,
        @NotNull
        List<RebelResourceDto> resources
) {
}

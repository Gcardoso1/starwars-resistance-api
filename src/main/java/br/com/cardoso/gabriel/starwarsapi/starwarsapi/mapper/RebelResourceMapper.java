package br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.RebelResource;
import org.mapstruct.factory.Mappers;

public interface RebelResourceMapper {
    RebelResourceMapper INSTANCE = Mappers.getMapper(RebelResourceMapper.class);

    RebelResource rebelResourceDtoToRebelResource(RebelResource rebelResourceDto);
}

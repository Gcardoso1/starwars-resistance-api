package br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.LocationDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location locationDtoToLocation(LocationDto locationDto);
}

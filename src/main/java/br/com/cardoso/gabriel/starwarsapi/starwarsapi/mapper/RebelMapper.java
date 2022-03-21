package br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper;


import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.RebelCreateDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RebelMapper {

    RebelMapper INSTANCE = Mappers.getMapper( RebelMapper.class );

    Rebel rebelCreateDtoToRebel(RebelCreateDto rebelCreateDto);
}

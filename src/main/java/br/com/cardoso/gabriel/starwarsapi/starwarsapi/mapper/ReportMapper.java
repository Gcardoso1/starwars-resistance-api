package br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.ReportDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    Report reportDtoToReport(ReportDto reportDto);
    ReportDto reportToReportDto(Report report);
}

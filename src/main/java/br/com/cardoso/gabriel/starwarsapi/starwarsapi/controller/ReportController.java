package br.com.cardoso.gabriel.starwarsapi.starwarsapi.controller;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.ReportDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Report;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.service.RebelService;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class ReportController {
    private final ReportService reportService;
    private final RebelService rebelService;

    public ReportController(ReportService reportService, RebelService rebelService) {
        this.reportService = reportService;
        this.rebelService = rebelService;
    }

    @PostMapping
    private ResponseEntity<String> reportRebel(@RequestBody @Valid ReportDto reportDto){
        Rebel accuser = rebelService.getById(reportDto.idAccuser());
        Rebel accused = rebelService.getById(reportDto.idAccused());
        Report report = Report.builder().accuser(accuser).accused(accused).build();
        reportService.reportRebel(report);
        return new ResponseEntity("Reported with successfully!", HttpStatus.CREATED);
    }
}

package br.com.cardoso.gabriel.starwarsapi.starwarsapi.controller;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.DashboardDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    private ResponseEntity<DashboardDto> getAll(){
        return ResponseEntity.ok(dashboardService.getAll());
    }
}

package br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto;

import java.util.Map;

public record DashboardDto(
        Map<String, Map<String, Double>> dashboardMap) {
}

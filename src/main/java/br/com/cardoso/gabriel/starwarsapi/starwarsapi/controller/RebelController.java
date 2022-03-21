package br.com.cardoso.gabriel.starwarsapi.starwarsapi.controller;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.LocationDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.NegotiationDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.RebelCreateDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper.LocationMapper;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.mapper.RebelMapper;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Location;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.RebelResource;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.service.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rebels")
@RequiredArgsConstructor
public class RebelController {

    private final RebelService rebelService;

    @GetMapping
    private ResponseEntity<List<Rebel>> getAll(){
        List<Rebel> rebelList = rebelService.getAll();
        return ResponseEntity.ok(rebelList);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Rebel> getById(@PathVariable("id") Integer id){
        Rebel rebel = rebelService.getById(id);
        return ResponseEntity.ok(rebel);
    }

    @PostMapping
    private ResponseEntity<Rebel> save(@RequestBody @Valid RebelCreateDto rebelCreateDto){
        Rebel newRebel = RebelMapper.INSTANCE.rebelCreateDtoToRebel(rebelCreateDto);
        List<RebelResource> resources = newRebel.getResources().stream()
                .collect(Collectors.groupingBy(RebelResource::getResource, Collectors.reducing((a, b) -> {
                    if (!a.getResource().equals(b.getResource())) return b;
                    a.setQuantity(a.getQuantity() + b.getQuantity());
                    return a;
                }))).values()
                .stream()
                .map(Optional::get).toList();

        Location location = LocationMapper.INSTANCE.locationDtoToLocation(rebelCreateDto.location());
        newRebel.setResources(resources);
        newRebel.setLocation(location);

        Rebel rebelAdded = rebelService.save(newRebel);
        return ResponseEntity.ok(rebelAdded);
    }

    @PatchMapping("/{id}/location")
    private ResponseEntity<String> updateLocation(
            @PathVariable("id") Integer id,
            @RequestBody @Valid LocationDto locationDto){
        Location updatedLocation = LocationMapper.INSTANCE.locationDtoToLocation(locationDto);
        return rebelService.updateLocation(id, updatedLocation);
    }

    @PostMapping("/{id}/negotiate/{idOtherRebel}")
    private ResponseEntity<String> tradeItens(
            @PathVariable("id") Integer id,
            @PathVariable("idOtherRebel") Integer idOtherRebel,
            @RequestBody @Valid NegotiationDto negotiationDto) {
        return rebelService.negotiateResources(id,idOtherRebel,negotiationDto);
    }
}

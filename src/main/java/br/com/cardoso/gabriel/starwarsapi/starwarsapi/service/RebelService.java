package br.com.cardoso.gabriel.starwarsapi.starwarsapi.service;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.NegotiationDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.dto.NegotiationItemDto;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.exception.*;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Location;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.RebelResource;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RebelService {
    private final RebelRepository rebelRepository;

    /////--------LISTA DE GET'S--------/////
    public List<Rebel> getAll(){ return rebelRepository.findAll();}
    public Rebel getById(Integer id) {
        return rebelRepository.findById(id).orElseThrow(RebelNotFoundException::new);
    }
    private Integer getTotalPoints(List<NegotiationItemDto> negotiationItemDtoList) {
        Integer totalPoints = negotiationItemDtoList.stream()
                .map(item -> item.quantity() * item.resource().getPoints())
                .reduce(0, (acc, act) -> acc += act);
        return totalPoints;
    }
    public Rebel save(Rebel rebel) {
        return rebelRepository.save(rebel);
    }

    private List<RebelResource> getResourceListNegotiated(
            Rebel rebel,
            List<NegotiationItemDto> negotiationItemDtoList,
            List<RebelResource> rebelResourceList) {
        List<RebelResource> newRebelResourceList = newResourceListWithoutSenders(rebel, negotiationItemDtoList, rebelResourceList);
        for (NegotiationItemDto negotiationItemDto : negotiationItemDtoList) {
            boolean exists = verifyIfResourceExists(negotiationItemDto, newRebelResourceList);
            if (!exists) {
                RebelResource rebelResource = createResource(rebel, negotiationItemDto);
                newRebelResourceList.add(rebelResource);
            }
        }
        return newRebelResourceList;
    }


    /////--------LISTA DE CREATE--------/////

    private RebelResource createResource(
            Rebel rebel,
            NegotiationItemDto negotiationItemDto) {
        return RebelResource.builder()
                .rebel(rebel)
                .resource(negotiationItemDto.resource())
                .quantity(negotiationItemDto.quantity())
                .build();
    }

    private List<RebelResource> newResourceListWithoutSenders(
            Rebel rebel,
            List<NegotiationItemDto> negotiationItemDtoList,
            List<RebelResource> rebelResourceList) {
        return rebelResourceList.stream()
                .map(meResource -> updateResource(rebel, negotiationItemDtoList, meResource))
                .collect(Collectors.toList());
    }

    /////--------LISTA DE UPDATE--------/////
    public ResponseEntity<String> negotiateResources(Integer id, Integer idOtherRebel, NegotiationDto negotiationDto) {

        if (id == idOtherRebel) throw new SelfNegotiationException();

        Rebel me = getById(id);
        Rebel other = getById(idOtherRebel);

        if (me.isBetrayer() || other.isBetrayer()) throw new BetrayerDetectedException();

        List<NegotiationItemDto> sendItens = negotiationDto.send();
        List<NegotiationItemDto> receiveItens = negotiationDto.receive();
        Integer meItensTotalPoints = getTotalPoints(sendItens);
        Integer otherItensTotalPoints = getTotalPoints(receiveItens);
        List<RebelResource> meResources = me.getResources();
        List<RebelResource> otherResources = other.getResources();

        boolean isEnough = hasEnoughResources(meResources, sendItens)
                && hasEnoughResources(otherResources, receiveItens);

        if (!isEnough) throw new RebelResourcesIsNotEnoughException();

        if (!(meItensTotalPoints.compareTo(otherItensTotalPoints) == 0)) throw new NegotiationResourcesPointsIsNotEqualsException();

        List<RebelResource> newMeResources = getResourceListNegotiated(me, receiveItens, meResources);
        List<RebelResource> newOtherResources = getResourceListNegotiated(other, sendItens, otherResources);

        me.setResources(newMeResources);
        rebelRepository.save(me);

        other.setResources(newOtherResources);
        rebelRepository.save(other);

        return new ResponseEntity<String>("The negotiation was successfully!", HttpStatus.OK);
    }

    public ResponseEntity<String> updateLocation(Integer id, Location location) {
        Rebel rebel = getById(id);
        location.setId(rebel.getLocation().getId());
        rebel.setLocation(location);
        rebelRepository.save(rebel);
        return new ResponseEntity<String>("Location updated with successfully", HttpStatus.NO_CONTENT);
    }

    private RebelResource updateResource(
            Rebel rebel,
            List<NegotiationItemDto> negotiationItemDtoList,
            RebelResource resourceToUpdate) {
        int newQuantity = negotiationItemDtoList.stream()
                .filter(negotiationItemDto -> negotiationItemDto.resource().equals(resourceToUpdate.getResource()))
                .map(NegotiationItemDto::quantity)
                .reduce(0, (acc, act) -> acc += act);
        resourceToUpdate.setId(resourceToUpdate.getId());
        resourceToUpdate.setRebel(rebel);
        resourceToUpdate.setQuantity(newQuantity);
        return resourceToUpdate;
    }



    /////--------LISTA DE BOOLEAN--------/////

    private boolean hasEnoughResources(
            List<RebelResource> resourceList,
            List<NegotiationItemDto> negotiationItemDtoList) {
        return negotiationItemDtoList.stream()
                .map(negotiationItemDto -> hasEnoughQuantity(negotiationItemDto, resourceList))
                .map(Boolean::booleanValue)
                .filter(booleanValue -> !booleanValue)
                .findFirst()
                .orElse(true);
    }

    private boolean hasEnoughQuantity(
            NegotiationItemDto itemDto,
            List<RebelResource> resourceList) {
        int quantity = resourceList.stream()
                .filter(resource -> resource.getResource().equals(itemDto.resource()))
                .map(RebelResource::getQuantity)
                .reduce(0, (acc, act) -> acc += act);
        return itemDto.quantity() <= quantity;
    }

    private boolean verifyIfResourceExists(
            NegotiationItemDto negotiationItemDto,
            List<RebelResource> rebelResourceList) {
        return rebelResourceList.stream()
                .map(RebelResource::getResource)
                .filter(rebelResourceItem -> rebelResourceItem.equals(negotiationItemDto.resource()))
                .findFirst()
                .isPresent();
    }
}

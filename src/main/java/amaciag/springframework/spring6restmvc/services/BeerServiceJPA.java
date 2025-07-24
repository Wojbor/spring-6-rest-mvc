package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.entities.Beer;
import amaciag.springframework.spring6restmvc.mappers.BeerMapper;
import amaciag.springframework.spring6restmvc.model.BeerDTO;
import amaciag.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        Beer savedBeer = beerMapper.beerDtoToBeer(beer);

        savedBeer.setCreatedDate(LocalDateTime.now());
        savedBeer.setUpdateDate(LocalDateTime.now());

        beerRepository.save(savedBeer);

        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {

        beerRepository.findById(beerId).ifPresent(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            foundBeer.setPrice(beer.getPrice());
            beerRepository.save(foundBeer);
        });

    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}

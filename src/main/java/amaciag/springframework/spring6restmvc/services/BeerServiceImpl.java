package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.entities.Beer;
import amaciag.springframework.spring6restmvc.mappers.BeerMapper;
import amaciag.springframework.spring6restmvc.model.BeerDTO;
import amaciag.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {


    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;


    @Override
    public List<BeerDTO> listBeers() {
        List<Beer> beerList = beerRepository.findAll();
        return beerList.stream().map(beerMapper::beerToBeerDto).toList();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.debug("Getting beer by Id - in service. Id: " + id.toString());

        Beer beer = beerRepository.findById(id).get();
        return Optional.of(beerMapper.beerToBeerDto(beer));
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

        Beer beerToUpdate = beerRepository.findById(beerId).get();
        beerToUpdate.setBeerName(beer.getBeerName());
        beerToUpdate.setBeerStyle(beer.getBeerStyle());
        beerToUpdate.setUpc(beer.getUpc());
        beerToUpdate.setQuantityOnHand(beer.getQuantityOnHand());
        beerToUpdate.setPrice(beer.getPrice());

        beerRepository.save(beerToUpdate);

    }

    @Override
    public void deleteById(UUID uuid) {
        beerRepository.deleteById(uuid);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {
        Beer existingBeer = beerRepository.findById(beerId).get();

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }

        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }

        beerRepository.save(existingBeer);
    }
}

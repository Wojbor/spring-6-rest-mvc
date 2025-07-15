package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.BeerDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Primary
public class BeerServiceJPA implements BeerService {
    @Override
    public List<BeerDTO> listBeers() {
        return List.of();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}

package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID uuid);

    void patchBeerById(UUID beerId, Beer beer);
}

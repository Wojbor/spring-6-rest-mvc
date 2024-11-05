package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Beer;
import amaciag.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;


    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Zimny Lokiec")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("15.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Mokra Maczeta")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1234563333")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(333)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Wojownik")
                .beerStyle(BeerStyle.PORTER)
                .upc("1236666")
                .price(new BigDecimal("15.99"))
                .quantityOnHand(444)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);

    }

    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Getting beer by Id - in service. Id: " + id.toString());

        /*return Beer.builder()
                .id(id)
                .beerName("Litovel")
                .version(1)
                .upc("123123")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(222)
                .beerStyle(BeerStyle.LAGER)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();*/

        return beerMap.get(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {

        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .version(beer.getVersion())
                .price(beer.getPrice())
                .build();

        beerMap.put(beer.getId(), savedBeer);

        return savedBeer;
    }
}

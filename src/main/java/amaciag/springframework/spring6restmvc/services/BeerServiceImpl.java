package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.BeerDTO;
import amaciag.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, BeerDTO> beerMap;


    @Override
    public List<BeerDTO> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
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

        BeerDTO beer2 = BeerDTO.builder()
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

        BeerDTO beer3 = BeerDTO.builder()
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
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Getting beer by Id - in service. Id: " + id.toString());

        /*return BeerDTO.builder()
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

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        BeerDTO savedBeer = BeerDTO.builder()
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

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO beerToUpdate = beerMap.get(beerId);
        beerToUpdate.setBeerName(beer.getBeerName());
        beerToUpdate.setBeerStyle(beer.getBeerStyle());
        beerToUpdate.setUpc(beer.getUpc());
        beerToUpdate.setQuantityOnHand(beer.getQuantityOnHand());
        beerToUpdate.setPrice(beer.getPrice());
        beerMap.put(beerToUpdate.getId(), beerToUpdate);

    }

    @Override
    public void deleteById(UUID uuid) {
        beerMap.remove(uuid);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existingBeer = beerMap.get(beerId);

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
    }
}

package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Beer;
import amaciag.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Getting beer by Id - in service. Id: " + id.toString());

        return Beer.builder()
                .id(id)
                .beerName("Litovel")
                .version(1)
                .upc("123123")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(222)
                .beerStyle(BeerStyle.LAGER)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
